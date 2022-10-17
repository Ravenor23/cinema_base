package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import com.kata.cinema.base.service.entity.impl.GenreService;

@Service
public class GenreServiceImp implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }
}
