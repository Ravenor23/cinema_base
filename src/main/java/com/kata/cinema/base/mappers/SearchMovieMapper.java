package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SearchMovieMapper {
    SearchMovieDto toDTO(Movie movie);

    Movie toEntity(SearchMovieDto searchMovieDto);
}
