package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.PaginationDtoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaginationDtoServiceImpl implements PaginationDtoService {

  private final PaginationDtoDao paginationDtoDao;

  @Autowired
  public PaginationDtoServiceImpl(PaginationDtoDao paginationDtoDao) {
    this.paginationDtoDao = paginationDtoDao;
  }


  @Override
  public PageDto getPageDto(Integer currentPage, Integer itemsOnPage) {
    PageDto pageDto = new PageDto<>();
    pageDto.setCount(paginationDtoDao.getResultTotal(null));
    pageDto.setEntities(paginationDtoDao.getItemsDto(currentPage,itemsOnPage, null));
    return pageDto;
  }

  @Override
  public PageDto getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage,
      Map parameters) {
    PageDto pageDto = new PageDto<>();
    pageDto.setCount(paginationDtoDao.getResultTotal(parameters));
    pageDto.setEntities(paginationDtoDao.getItemsDto(currentPage,itemsOnPage, parameters));
    return pageDto;
  }
}
