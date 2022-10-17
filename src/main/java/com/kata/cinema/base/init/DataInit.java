package com.kata.cinema.base.init;

import com.kata.cinema.base.models.entity.Collection;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.entity.impl.CollectionServiceImp;
import com.kata.cinema.base.service.entity.impl.GenreService;
import com.kata.cinema.base.service.entity.impl.MovieService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnExpression("${app.initializer.runInitialize}")
public class DataInit {
    private final MovieService movieService;
    private final GenreService genreService;
    private final CollectionServiceImp collectionService;

    public DataInit(MovieService movieService, GenreService genreService, CollectionServiceImp collectionService) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.collectionService = collectionService;
    }

    @PostConstruct
    public void init() {
        createGenre();
        createMovie();
        createCollection();
    }

    public void createGenre() {
        for (int i = 1; i <= 10; i++) {
            genreService.save(new Genre("Жанр" + i));
        }
    }

    public void createMovie() {
        for (int i = 1; i <= 100; i++) {
            Movie movie = new Movie();
            movie.setName("фильм" + i);
            movie.setDataRelease(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                    .nextLong(LocalDate.of(1990, Month.JANUARY, 1).toEpochDay(),
                            LocalDate.now().toEpochDay())));
            movie.setDescription("описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма");

            List<MPAA> mpaaList = Arrays.asList(MPAA.values());
            movie.setMpaa(mpaaList.get(new SecureRandom().nextInt(mpaaList.size())));

            List<RARS> rarsList = Arrays.asList(RARS.values());
            movie.setRars(rarsList.get(new SecureRandom().nextInt(rarsList.size())));

            List<Genre> genreList = new ArrayList<>(genreService.getAll());
            int randomSize = ThreadLocalRandom.current().nextInt(1, 4);
            Collections.shuffle(genreList);
            movie.setGenres(new HashSet<>(genreList.subList(genreList.size() - randomSize, genreList.size())));

            movieService.save(movie);
        }
    }

    public void createCollection() {
            for (int i = 1; i <= 20; i++) {
                boolean enable = !Arrays.asList(2, 6, 10, 14, 18).contains(i);
                Collection collection = new Collection("Коллекция" + i, enable);

                List<Movie> movieList = new ArrayList<>(movieService.getAll());
                int randomSize = ThreadLocalRandom.current().nextInt(5, 16);
                Collections.shuffle(movieList);
                collection.setMovies(new HashSet<>(movieList.subList(movieList.size() - randomSize, movieList.size())));

                collectionService.save(collection);
            }
    }

}
