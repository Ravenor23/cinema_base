package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;

public interface MovieDtoService {

    PageDto<MovieResponseDto> getMoviesAfishaByGenre(Long genreId);
}
