package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.request.PersonRequestDto;

public interface PersonRequestDtoService {

    void save(PersonRequestDto personRequestDto);

    void update(Long id, PersonRequestDto personRequestDto);

    void delete(Long id);
}
