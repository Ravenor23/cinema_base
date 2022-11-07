package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class PersonRestController {

  private final PersonViewResponseDtoService personViewResponseDtoService;

  @Autowired
  public PersonRestController(PersonViewResponseDtoService personViewResponseDtoService) {
    this.personViewResponseDtoService = personViewResponseDtoService;
  }

  @GetMapping("/api/persons/{id}")
  public PersonViewResponseDto getPerson(@PathVariable("id") Long personId) {
    return personViewResponseDtoService.findPersonById(personId);
  }

}
