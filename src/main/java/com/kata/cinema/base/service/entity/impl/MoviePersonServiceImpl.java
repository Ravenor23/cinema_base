package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.MoviePerson;
import com.kata.cinema.base.repositories.MoviePersonRepository;
import com.kata.cinema.base.service.entity.MoviePersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviePersonServiceImpl implements MoviePersonService {

    private final MoviePersonRepository moviePersonRepository;

    public MoviePersonServiceImpl(MoviePersonRepository moviePersonRepository) {
        this.moviePersonRepository = moviePersonRepository;
    }

    @Override
    public List<MoviePerson> getByProfessionId(long professionId) {
        return moviePersonRepository.getByProfessionId(professionId);
    }

    @Override
    public void save(MoviePerson moviePerson) {
        moviePersonRepository.save(moviePerson);
    }
}
