package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.ProductionMovieStudioResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionMovieStudioRepository extends JpaRepository<ProductionMovieStudioResponseDto, Long> {
}