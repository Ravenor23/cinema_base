package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.repositories.ProductionMovieStudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductionMovieStudioService {

    private final ProductionMovieStudioRepository productionMovieStudioRepository;

    @Autowired
    public ProductionMovieStudioService(ProductionMovieStudioRepository productionMovieStudioRepository) {
        this.productionMovieStudioRepository = productionMovieStudioRepository;
    }

    public ProductionMovieStudioResponseDto getProductionMovieStudioResponseDto(Long id) {
        return productionMovieStudioRepository.getById(id);
    }
}