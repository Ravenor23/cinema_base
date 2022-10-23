package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.TopMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopMovieRepository extends JpaRepository<TopMovie, Long> {

    @Modifying
    @Query("delete from TopMovie where movie.id >= 0")
    void deleteAll();
}
