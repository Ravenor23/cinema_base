package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.repositories.PaginationDtoDao;
import com.kata.cinema.base.service.PersonPaginationDtoService;
import org.springframework.stereotype.Service;

@Service
public class PersonPaginationServiceImpl extends PaginationDtoServiceImpl<PersonViewResponseDto> implements
    PersonPaginationDtoService {

  public PersonPaginationServiceImpl(
      PaginationDtoDao<PersonViewResponseDto> paginationDtoDao) {
    super(paginationDtoDao);
  }
}
