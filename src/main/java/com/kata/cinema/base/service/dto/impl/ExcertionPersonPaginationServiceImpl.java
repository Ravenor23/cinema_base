package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.repositories.pagination.PaginationDtoDao;
import com.kata.cinema.base.service.dto.ExcertionMoviePaginationDtoService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExcertionPersonPaginationServiceImpl extends PaginationDtoServiceImpl<ExcertionResponseDto> implements ExcertionMoviePaginationDtoService {

    public ExcertionPersonPaginationServiceImpl(PaginationDtoDao<ExcertionResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }

    @Override
    public PageDto<ExcertionResponseDto> getPageDto(Integer currentPage, Integer itemsOnPage) {
        return super.getPageDto(currentPage, itemsOnPage);
    }

    @Override
    public PageDto<ExcertionResponseDto> getPageDtoWithParameters(Integer currentPage,
                                                                  Integer itemsOnPage, Map parameters) {
        return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }
}
