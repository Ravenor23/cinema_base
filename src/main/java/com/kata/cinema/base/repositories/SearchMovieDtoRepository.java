package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMovieDtoRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    default List<Movie> findTop3byNameContainingIgnoreCase(String name) {
        return findAllByNameContainingIgnoreCase(name, PageRequest.of(0, 3));
    }
}