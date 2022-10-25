package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.service.ScorePaginationDtoService;
import org.springframework.stereotype.Service;
import com.kata.cinema.base.repositories.PaginationDtoDao;

@Service
public class ScoreMoviePaginationServiceImpl extends PaginationDtoServiceImpl<ScoreMovieResponseDto> implements ScorePaginationDtoService {

    public ScoreMoviePaginationServiceImpl(PaginationDtoDao<ScoreMovieResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }
}
