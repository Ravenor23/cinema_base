package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.repositories.PersonRepository;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

   private final PersonRepository personRepository;

    PersonServiceImpl(PersonRepository personRepository){
        this.personRepository=  personRepository;
    }
    @Override
    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person getById(Long id) {
        return personRepository.getById(id);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

}
