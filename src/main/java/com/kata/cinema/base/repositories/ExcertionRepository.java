package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Excertion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcertionRepository extends JpaRepository<Excertion, Long> {


}
