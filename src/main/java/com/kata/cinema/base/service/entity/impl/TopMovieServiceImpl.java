package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.TopMovie;
import com.kata.cinema.base.repositories.TopMovieRepository;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.TopMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopMovieServiceImpl implements TopMovieService {

    private final TopMovieRepository topMovieRepository;
    private final MovieService movieService;

    public TopMovieServiceImpl(TopMovieRepository topMovieRepository, MovieService movieService) {
        this.topMovieRepository = topMovieRepository;
        this.movieService = movieService;
    }

    @Transactional
    public void save(TopMovie topMovie) {
        TopMovie top = new TopMovie();
        top.setRating(topMovie.getRating());
        top.setMovie(movieService.findById(topMovie.getMovie().getId()));
        topMovieRepository.save(top);
    }

    @Transactional
    public void deleteAll() {
        topMovieRepository.deleteAll();
    }
}
