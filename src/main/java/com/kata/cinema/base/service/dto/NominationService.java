package com.kata.cinema.base.service.dto;


import com.kata.cinema.base.models.entity.Nomination;
import java.util.List;


public interface NominationService {

    Nomination findNominationById(Long id);

    void createNomination(Nomination nomination);

    List<Nomination> getAll();

    void deleteById(Long id);
}
