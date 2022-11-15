package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.repositories.pagination.ExcertionMoviePaginationDtoDao;
import com.kata.cinema.base.repositories.pagination.PaginationDtoDao;
import com.kata.cinema.base.service.dto.ExcertionMoviePaginationDtoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExcertionMoviePaginationServiceImpl extends PaginationDtoServiceImpl<ExcertionResponseDto> implements ExcertionMoviePaginationDtoService {

    public ExcertionMoviePaginationServiceImpl(ExcertionMoviePaginationDtoDao paginationDtoDao) {
        super(paginationDtoDao);
    }
}
