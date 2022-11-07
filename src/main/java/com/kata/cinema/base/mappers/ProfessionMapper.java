package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.entity.Profession;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessionMapper {

  ProfessionResponseDto toDTO(Profession profession);

  List<ProfessionResponseDto> modelsToDTO(List<Profession> professionList);

  Profession toEntity(ProfessionResponseDto professionResponseDto);

}
