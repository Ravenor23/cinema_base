package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.SearchMovieDto;
import com.kata.cinema.base.models.entity.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = SearchMovieMapper.class)
public interface SearchMovieListMapper {

    List<SearchMovieDto> toDTOList(List<Movie> movieList);

    List<Movie> toEntityList(List<SearchMovieDto> searchMovieDtoList);
}
