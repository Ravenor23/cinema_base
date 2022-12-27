package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Person getPersonById(Long id);
}
