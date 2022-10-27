package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.entity.Excertion;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper(componentModel = "spring")
public interface ExcertionResponseMapper {
    ExcertionResponseDto toDTO(Excertion excertion);

    List<ExcertionResponseDto> modelsToDTO(List<Excertion> excertions);

    Excertion toEntity(ExcertionResponseDto excertionResponseDto);
}
