package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMovieDtoRepository extends JpaRepository<SearchMovieDto, Long> {

    List<Movie> findAllByNameContainingIgnoreCase(String name);

    List<Movie> findAllByOriginalNameContainingIgnoreCase(String originalName);
}