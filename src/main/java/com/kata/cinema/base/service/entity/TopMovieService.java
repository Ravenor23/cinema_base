package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.TopMovie;

public interface TopMovieService {
    void save(TopMovie topMovie);
    void deleteAll();
}
