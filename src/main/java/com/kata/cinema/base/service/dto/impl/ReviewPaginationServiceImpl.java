package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.service.ReviewPaginationDtoService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ReviewPaginationServiceImpl extends PaginationDtoServiceImpl<ReviewResponseDto> implements
    ReviewPaginationDtoService<ReviewResponseDto> {

  public ReviewPaginationServiceImpl(PaginationDtoDao<ReviewResponseDto> paginationDtoDao) {
    super(paginationDtoDao);
  }

  @Override
  public PageDto<ReviewResponseDto> getPageDto(Integer currentPage, Integer itemsOnPage) {
    return super.getPageDto(currentPage, itemsOnPage);
  }

  @Override
  public PageDto<ReviewResponseDto> getPageDtoWithParameters(Integer currentPage,
      Integer itemsOnPage, Map parameters) {
    return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
  }
}
