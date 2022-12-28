package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.GenreMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByName(String name);

    @Query("from Movie where dataRelease =:currentDate")
    List<Movie> findByDataRelease(LocalDate currentDate);


    @Query("select new com.kata.cinema.base.models.dto.response.GenreMovieDto(m.id, g.name) from Movie m join m.genres g where m.id in :movieId")
    List<GenreMovieDto> getMoviesById(List<Long> movieId);
}
