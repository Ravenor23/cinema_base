package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ExcertionRequestMapper;
import com.kata.cinema.base.mappers.ExcertionResponseMapper;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.entity.Excertion;
import com.kata.cinema.base.repositories.ExcertionRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableAutoConfiguration
public class ExcertionService {

    private final ExcertionRepository excertionRepository;
    private final ExcertionRequestMapper excertionRequestMapper;
    private final ExcertionResponseMapper excertionResponseMapper;

    public ExcertionService(ExcertionRepository excertionRepository, ExcertionRequestMapper excertionRequestMapper, ExcertionResponseMapper excertionResponseMapper) {
        this.excertionRepository = excertionRepository;
        this.excertionRequestMapper = excertionRequestMapper;
        this.excertionResponseMapper = excertionResponseMapper;
    }


    public PageDto<ExcertionResponseDto> findExcertionPageDtoById(Long id) {
        return (PageDto<ExcertionResponseDto>) excertionResponseMapper.modelsToDTO(excertionRepository.findById(id));
    }

    public void saveExcertion(ExcertionRequestDto excertionRequestDto) {
        Excertion excertion = new Excertion();
        excertionRequestDto.setDescription(excertionRequestDto.getDescription());
        excertionRepository.save(excertionRequestMapper.toEntity(excertionRequestDto));
    }

    public void updateExcertion(Long id, ExcertionRequestDto excertionRequestDto) {
        Excertion excertion = excertionRepository.getById(id);
        excertionRequestDto.setDescription(excertionRequestDto.getDescription());
        excertionRepository.save(excertionRequestMapper.toEntity(excertionRequestDto));
    }

    public void deleteExcertion(Long id) {
        excertionRepository.deleteById(id);
    }
}
