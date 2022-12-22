package com.kata.cinema.base.service.dto.impl;


import com.kata.cinema.base.models.dto.response.ScoreMovieV2ResponseDto;
import com.kata.cinema.base.repositories.pagination.PaginationDtoDao;
import com.kata.cinema.base.service.dto.StudioMoviesPaginationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudioMoviesPaginationServiceImpl extends PaginationDtoServiceImpl<ScoreMovieV2ResponseDto> implements StudioMoviesPaginationService {

    public StudioMoviesPaginationServiceImpl(@Qualifier("studioMoviesPaginationDtoDaoImpl") PaginationDtoDao<ScoreMovieV2ResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }
}
