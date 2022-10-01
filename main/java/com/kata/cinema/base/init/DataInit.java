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
import java.util.*;

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
    public void createMovie() {
        List<Genre> listGenre = new ArrayList<>();
        List<Collection> listCollection = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            Genre genre = new Genre();
            genre.setName("Genre" + i);
            genreService.save(genre);
            listGenre.add(genre);
        }
        for (int i = 1; i < 21; i++) {
            Collection collection = new Collection();
            collection.setName("Collection" + i);
            if (i >= 15) {
                collection.setEnable(false);
            } else {
                collection.setEnable(true);
            }
            collectionService.save(collection);
            listCollection.add(collection);
        }
        for (int i = 1; i < 101; i++) {
            Movie movie = new Movie();
            movie.setName("Movie" + i);
            movie.setDataRelease(String.valueOf(1980 + (int) (Math.random() * 42)));
            movie.setTime(String.valueOf(100 + (int) (Math.random() * 80)));
            movie.setDescription("описание описание описание описание описание описание описание описание " +
                    "описание описание описание описание");
            movie.setMpaa(MPAA.generateRandomMPAA());
            movie.setRars(RARS.generateRandomRARS());
            Collections.shuffle(listGenre);
            movie.setGenres(listGenre.subList(0, 3));
            movie.setCollections(listCollection.subList(5, 15));
            movieService.save(movie);
        }
    }
}










