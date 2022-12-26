package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.entity.Excertion;

import java.util.List;

public interface ExcertionService {

    void save(Excertion excertion);

    List<Excertion> getAll();

    Excertion findById(Long id);

    void update(Long id, Excertion excertion);

    void delete(Long id);
}
