package com.kata.cinema.base.configs;

import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.models.entity.TopMovie;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.ScoreService;
import com.kata.cinema.base.service.entity.TopMovieService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(value = "com.kata.cinema.base")
public class TopMovieConfig {

    private final TopMovieService topMovieService;
    private final ScoreService scoreService;
    private final MovieService movieService;

    public TopMovieConfig(TopMovieService topMovieService, ScoreService scoreService, MovieService movieService) {
        this.topMovieService = topMovieService;
        this.scoreService = scoreService;
        this.movieService = movieService;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Async
    @Scheduled(cron = "0 0 0 * * SUN")
    public void set() {

        topMovieService.deleteAll();

        List<Score> scoreList = scoreService.getAll();
        List<Score> uniqScoreByMovieId = scoreList.stream()
                .filter(distinctByKey(score -> score.getMovie().getId())).toList();

        List<TopMovie> topMovieList = new ArrayList<>();
        for (Score score : uniqScoreByMovieId) {
            long count = scoreList.stream()
                    .filter(s -> s.getMovie().getId().equals(score.getMovie().getId())).count();
            if (count >= 20) {
                TopMovie topMovie = new TopMovie();

                double rating = scoreList.stream()
                        .filter(s -> s.getMovie().getId().equals(score.getMovie().getId()))
                        .mapToInt(Score::getScore).average().orElse(0);
                topMovie.setRating(Math.toIntExact(Math.round(rating)));

                long movieId = scoreService.findById(score.getId()).getMovieId();
                topMovie.setMovie(movieService.findById(movieId));

                topMovieList.add(topMovie);
            }
        }

        List<TopMovie> top = topMovieList.stream()
                .sorted(Comparator.comparing(TopMovie::getRating).reversed())
                .limit(50).toList();

        for (TopMovie topMovie : top) {
            topMovieService.save(topMovie);
        }
    }
}