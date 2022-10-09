package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.ProductionStudioMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionMovieStudioRepository extends JpaRepository<ProductionStudioMovie, Long> {
}