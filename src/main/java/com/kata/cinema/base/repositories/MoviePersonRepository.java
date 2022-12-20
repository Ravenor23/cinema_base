package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.MoviePerson;
import com.kata.cinema.base.models.entity.MoviePerson.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Id> {

}
