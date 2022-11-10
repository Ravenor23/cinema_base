package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.PurchasedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedMovieRepository extends JpaRepository<PurchasedMovie, Long> {
}
