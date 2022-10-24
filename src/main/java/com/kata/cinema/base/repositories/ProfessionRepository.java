package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.models.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
