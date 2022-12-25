package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;


public interface ExcertionDtoService {

    void saveExcertion(ExcertionRequestDto excertionRequestDto);

    void updateExcertion(Long id, ExcertionRequestDto excertionRequestDto) throws Exception;

    void deleteExcertion(Long id);
}
