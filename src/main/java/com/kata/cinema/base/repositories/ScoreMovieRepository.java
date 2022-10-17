package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMovieRepository extends JpaRepository<Score, Long> {
}
