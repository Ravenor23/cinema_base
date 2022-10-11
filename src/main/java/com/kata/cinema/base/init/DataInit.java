package com.kata.cinema.base.init;

import com.kata.cinema.base.models.entity.Collection;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.entity.MPAA;
import com.kata.cinema.base.models.entity.RARS;
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

    List<Genre> listGenre = new ArrayList<>();
    List<Collection> listCollection = new ArrayList<>();

    @PostConstruct
    public void createGenre() {
        Genre genre = new Genre();
        for (int i = 1; i < 11; i++) {
            genre.setName("Genre" + i);
            genreService.save(genre);
            listGenre.add(genre);
        }
    }

    @PostConstruct
    public void createCollection() {
        Collection collection = new Collection();
        for (int i = 1; i < 21; i++) {
            collection.setName("Collection" + i);
            if (i >= 15) {
                collection.setEnable(false);
            } else {
                collection.setEnable(true);
            }

            collectionService.save(collection);
            listCollection.add(collection);
        }
    }

    @PostConstruct
    public void createMovie() {
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
            if (i % 2 == 0) {
                movie.setGenres(listGenre.subList(0, 3));
            } else {
                movie.setGenres(listGenre.subList(0, 1));
            }

            Collections.shuffle(listCollection);
            if (i % 2 == 0) {
                movie.setCollections(listCollection.subList(0, 15));
            } else {
                movie.setCollections(listCollection.subList(0, 5));
            }
            movieService.save(movie);
        }
    }

}
