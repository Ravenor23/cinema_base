package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.entity.MoviePerson;

import java.util.List;

public interface MoviePersonService {

    List<MoviePerson> getByProfessionId(long professionId);

    void save(MoviePerson moviePerson);
}
