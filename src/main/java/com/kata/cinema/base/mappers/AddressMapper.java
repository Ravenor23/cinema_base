package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.dto.response.AddressResponseDto;
import com.kata.cinema.base.models.entity.Address;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponseDto toDTO(Address genre);

    List<AddressResponseDto> modelsToDTO(List<Address> genres);

    Address toEntity(AddressRequestDto addressRequestDto);
}
