package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.AvailableOnlineMovie;
import com.kata.cinema.base.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableOnlineMovieRepository extends JpaRepository<AvailableOnlineMovie, Long> {

    AvailableOnlineMovie findAvailableOnlineMovieByMovie(Movie movie);

}
