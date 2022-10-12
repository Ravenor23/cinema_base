package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImp implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }
}
