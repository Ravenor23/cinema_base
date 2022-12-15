package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ContentResponseDto;
import com.kata.cinema.base.models.entity.Content;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    @Mapping(target = "typeContent", source = "type")
    ContentResponseDto toDTO(Content genre);

    List<ContentResponseDto> modelsToDTO(List<Content> genres);
}
