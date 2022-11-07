package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.mappers.MovieMapper;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.repositories.MovieRepository;
import com.kata.cinema.base.service.entity.MovieService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;

    private  final MovieMapper movieMapper;

    public MovieServiceImp(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void save(MovieRequestDto movieRequestDto) {
        movieRepository.save(movieMapper.toEntity(movieRequestDto));
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Movie getMovieByName(String name) {
        return movieRepository.findMovieByName(name);
    }
}
