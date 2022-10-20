package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.service.PaginationDtoService;
import com.kata.cinema.base.service.ScorePaginationDtoService;

import java.util.Map;

public class ScoreMoviePaginationServiceImpl extends PaginationDtoServiceImpl<ScoreMovieResponseDto> implements ScorePaginationDtoService {

    public ScoreMoviePaginationServiceImpl(PaginationDtoDao<ScoreMovieResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }

    @Override
    public PageDto<ScoreMovieResponseDto> getPageDto(Integer currentPage, Integer itemsOnPage) {
        return super.getPageDto(currentPage, itemsOnPage);
    }

    @Override
    public PageDto<ScoreMovieResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map parameters) {
        return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }
}
