package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.PurchasedMovie;
import com.kata.cinema.base.repositories.PurchasedMovieRepository;
import com.kata.cinema.base.service.entity.PurchasedMovieService;
import org.springframework.stereotype.Service;

@Service
public class PurchasedMovieImpl implements PurchasedMovieService {
    private final PurchasedMovieRepository purchasedMovieRepository;

    public PurchasedMovieImpl(PurchasedMovieRepository purchasedMovieRepository) {
        this.purchasedMovieRepository = purchasedMovieRepository;
    }

    @Override
    public void save(PurchasedMovie purchasedMovie) {
        purchasedMovieRepository.save(purchasedMovie);
    }

}
