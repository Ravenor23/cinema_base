package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Excertion;
import com.kata.cinema.base.repositories.ExcertionRepository;
import com.kata.cinema.base.service.entity.ExcertionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExcertionServiceImpl implements ExcertionService {

    private final ExcertionRepository excertionRepository;

    public ExcertionServiceImpl(ExcertionRepository excertionRepository) {
        this.excertionRepository = excertionRepository;
    }

    @Override
    public void save(Excertion excertion) {excertionRepository.save(excertion); }

    @Override
    public List<Excertion> getAll() {return excertionRepository.findAll(); }

    @Override
    public Excertion findById(Long id) {return excertionRepository.findById(id).orElseThrow(NoSuchElementException::new); }

    @Override
    public void update(Long id, Excertion excertion) {
        Excertion updatedExcertion = excertionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        updatedExcertion.setDescription(excertion.getDescription());
        updatedExcertion.setMovie(excertion.getMovie());
        updatedExcertion.setPerson(excertion.getPerson());
        save(updatedExcertion);
    }

    @Override
    public void delete(Long id) {excertionRepository.deleteById(id); }
}
