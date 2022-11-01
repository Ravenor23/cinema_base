package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.Person;

import java.util.List;

public interface PersonService {
    void save(Person person);
    Person getById (Long id);
    List<Person> getAll();
}
