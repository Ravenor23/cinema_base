package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.entity.ProductionStudioMovie;
import com.kata.cinema.base.repositories.ProductionMovieStudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ProductionMovieStudioService {

    private final ProductionMovieStudioRepository productionMovieStudioRepository;

    @Autowired
    public ProductionMovieStudioService(ProductionMovieStudioRepository productionMovieStudioRepository) {
        this.productionMovieStudioRepository = productionMovieStudioRepository;
    }

    @Transactional
    public ProductionMovieStudioResponseDto getProductionMovieStudioResponseDto(Long id) {
        ProductionMovieStudioResponseDto productionMovieStudioResponseDto = new ProductionMovieStudioResponseDto();
        ProductionStudioMovie productionStudioMovie = productionMovieStudioRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        productionMovieStudioResponseDto.setName(productionStudioMovie.getMovie().getName());
        productionMovieStudioResponseDto.setStudioPerformance(productionStudioMovie.getPerformance());
        return productionMovieStudioResponseDto;
    }
}