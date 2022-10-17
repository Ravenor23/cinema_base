package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import com.kata.cinema.base.service.entity.impl.MovieService;

@Service
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}
