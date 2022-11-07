package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.entity.Genre;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponseDto toDTO (Genre genre);

    List<GenreResponseDto> modelsToDTO (List<Genre> genres);

    Genre toEntity (GenreResponseDto genreResponseDto);

}
