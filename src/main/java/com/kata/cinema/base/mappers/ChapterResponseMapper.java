package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.entity.Chapter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChapterResponseMapper {

    List<ChapterResponseDto> modelsToDTO(List<Chapter> chapterList);

}
