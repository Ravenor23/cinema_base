package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.dto.response.AddressResponseDto;
import java.util.List;

public interface AddressDtoService {

    List<AddressResponseDto> getAll();

    void save(AddressRequestDto addressRequestDto);

    void update(Long id, AddressRequestDto addressRequestDto);

    void delete(Long id);
}
