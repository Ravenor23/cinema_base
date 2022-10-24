package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.repositories.PersonRepository;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
