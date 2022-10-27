package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.entity.Excertion;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ExcertionRequestMapper {
    ExcertionRequestDto toDTO(Excertion excertion);
    List<ExcertionRequestDto> modelsToDTO(List<Excertion> excertions);
    Excertion toEntity(ExcertionRequestDto excertionRequestDto);
}