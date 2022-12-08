package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.PersonRequestDtoMapper;
import com.kata.cinema.base.models.dto.request.PersonRequestDto;
import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.repositories.PersonRepositories;
import com.kata.cinema.base.service.dto.PersonRequestDtoService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class PersonRequestDtoServiceImpl implements PersonRequestDtoService {

    private final PersonRepositories personRepositories;
    private final PersonRequestDtoMapper personRequestDtoMapper;

    public PersonRequestDtoServiceImpl(PersonRepositories personRepositories,
                                       PersonRequestDtoMapper personRequestDtoMapper) {
        this.personRepositories = personRepositories;
        this.personRequestDtoMapper = personRequestDtoMapper;
    }

    @Override
    public void save(PersonRequestDto personRequestDto) {
        personRepositories.save(personRequestDtoMapper.toEntity(personRequestDto));
    }

    @Override
    public void update(Long id, PersonRequestDto personRequestDto) {
        if (personRepositories.getPersonById(id) == null) {
            throw new NoSuchElementException();
        }
        Person person = personRequestDtoMapper.toEntity(personRequestDto);
        person.setId(id);
        personRepositories.save(person);
    }

    @Override
    public void delete(Long id) {
        Person person = personRepositories.findById(id).orElseThrow(NoSuchElementException::new);
        personRepositories.delete(person);
    }
}
