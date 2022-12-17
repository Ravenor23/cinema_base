package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.entity.Nomination;
import com.kata.cinema.base.repositories.NominationRepository;
import org.springframework.stereotype.Service;
import com.kata.cinema.base.service.dto.NominationService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NominationServiceImpl implements NominationService {

    private final NominationRepository nominationRepository;


    public NominationServiceImpl(NominationRepository nominationRepository) {
        this.nominationRepository = nominationRepository;
    }

    @Override
    public Nomination findNominationById(Long id) {
        return nominationRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void createNomination(Nomination nomination) {
        nominationRepository.save(nomination);
    }


    @Override
    public List<Nomination> getAll() {
        return nominationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {nominationRepository.deleteById(id);}
}
