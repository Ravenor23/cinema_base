package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.repositories.pagination.PaginationDtoDao;
import com.kata.cinema.base.service.dto.ExcertionPersonPaginationDtoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExcertionPersonPaginationServiceImpl extends PaginationDtoServiceImpl<ExcertionResponseDto> implements
    ExcertionPersonPaginationDtoService {

    public ExcertionPersonPaginationServiceImpl(@Qualifier("excertionPersonPaginationDtoDaoImpl")PaginationDtoDao<ExcertionResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }

}
