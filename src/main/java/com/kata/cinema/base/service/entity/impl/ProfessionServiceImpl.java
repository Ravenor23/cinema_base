package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Profession;
import com.kata.cinema.base.repositories.ProfessionRepository;
import com.kata.cinema.base.service.entity.ProfessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessionServiceImpl implements ProfessionService {

   private final ProfessionRepository professionRepository;

    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    @Transactional
    public void save(Profession profession) {
        professionRepository.save(profession);
    }
}
