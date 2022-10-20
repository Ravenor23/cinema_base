package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.PaginationDtoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaginationDtoServiceImpl<T> implements PaginationDtoService<T> {

  private final PaginationDtoDao<T> paginationDtoDao;

  @Autowired
  public PaginationDtoServiceImpl(PaginationDtoDao<T> paginationDtoDao) {
    this.paginationDtoDao = paginationDtoDao;
  }


  @Transactional
  @Override
  public PageDto<T> getPageDto(Integer currentPage, Integer itemsOnPage) {
    PageDto<T> pageDto = new PageDto<>();
    pageDto.setCount(paginationDtoDao.getResultTotal(new HashMap<>()));
    pageDto.setEntities(paginationDtoDao.getItemsDto(currentPage,itemsOnPage, new HashMap<>()));
    return pageDto;
  }

 @Transactional
  @Override
  public PageDto<T> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map parameters) {
    PageDto<T> pageDto = new PageDto<>();
    pageDto.setCount(paginationDtoDao.getResultTotal(parameters));
    pageDto.setEntities(paginationDtoDao.getItemsDto(currentPage,itemsOnPage, parameters));
    return pageDto;
  }
}
