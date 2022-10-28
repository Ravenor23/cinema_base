package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ExcertionRequestMapper;
import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.entity.Excertion;
import com.kata.cinema.base.repositories.ExcertionRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@EnableAutoConfiguration
public class ExcertionServiceImpl {

    private final ExcertionRepository excertionRepository;
    private final ExcertionRequestMapper excertionRequestMapper;

    public ExcertionServiceImpl(ExcertionRepository excertionRepository, ExcertionRequestMapper excertionRequestMapper) {
        this.excertionRepository = excertionRepository;
        this.excertionRequestMapper = excertionRequestMapper;
    }

    public void saveExcertion(ExcertionRequestDto excertionRequestDto) {
        Excertion excertion = new Excertion();
        excertionRequestDto.setDescription(excertionRequestDto.getDescription());
        excertionRepository.save(excertionRequestMapper.toEntity(excertionRequestDto));
    }

    public void updateExcertion(Long id, ExcertionRequestDto excertionRequestDto) throws Exception {
        try {
            Excertion excertion = excertionRepository.getById(id);
            excertionRequestDto.setDescription(excertionRequestDto.getDescription());
            excertionRepository.save(excertionRequestMapper.toEntity(excertionRequestDto));
        } catch (Exception e) {
            throw new Exception();
        }

    }

    public void deleteExcertion(Long id) {
        excertionRepository.deleteById(id);
    }
}
