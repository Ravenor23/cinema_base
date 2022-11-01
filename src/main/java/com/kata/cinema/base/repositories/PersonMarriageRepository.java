package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.PersonMarriage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonMarriageRepository extends JpaRepository<PersonMarriage, Long> {
}
