package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface SearchMovieMapper {

    SearchMovieDto toDTO(Movie movie);

    default List<SearchMovieDto> modelsToDTO(List<Movie> movies){
        return movies.stream().map(this::toDTO).toList();
    }

    Movie toEntity(SearchMovieDto searchMovieDto);
}
