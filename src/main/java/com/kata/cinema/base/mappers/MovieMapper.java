package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  @Mapping(target = "originName", source = "originalName")
  @Mapping(target = "dataRelease", source = "dateRelease")
  @Mapping(target = "genres", source = "genreIds")
  Movie toEntity(MovieRequestDto movieRequestDto);

}
