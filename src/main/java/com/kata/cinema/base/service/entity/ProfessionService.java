package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.Profession;

import java.util.List;

public interface ProfessionService {
    void save(Profession profession);
    Profession getById(Long id);

    List <Profession> getProfessions();

    void deleteProfession(long professionId);
}
