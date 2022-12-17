package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.repositories.pagination.PaginationDtoDao;
import com.kata.cinema.base.service.dto.ReviewPaginationDtoService;
import org.springframework.stereotype.Service;

@Service
public class ReviewPaginationServiceImpl extends PaginationDtoServiceImpl<ReviewResponseDto>
        implements ReviewPaginationDtoService {

  public ReviewPaginationServiceImpl(PaginationDtoDao<ReviewResponseDto> paginationDtoDao) {
    super(paginationDtoDao);
  }
}
