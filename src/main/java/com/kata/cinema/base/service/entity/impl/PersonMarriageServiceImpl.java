package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.PersonMarriage;
import com.kata.cinema.base.repositories.PersonMarriageRepository;
import com.kata.cinema.base.service.entity.PersonMarriageService;
import org.springframework.stereotype.Service;

@Service
public class PersonMarriageServiceImpl implements PersonMarriageService {

    private final PersonMarriageRepository personMarriageRepository;

    public PersonMarriageServiceImpl(PersonMarriageRepository personMarriageRepository) {
        this.personMarriageRepository = personMarriageRepository;
    }

    @Override
    public void save(PersonMarriage personMarriage) {
        personMarriageRepository.save(personMarriage);
    }
}
