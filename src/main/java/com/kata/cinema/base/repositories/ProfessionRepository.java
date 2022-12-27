package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Person;
import com.kata.cinema.base.models.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    @Query("select p.professions from Person p where p.id = ?1 ")
    Set<Profession> getProfessionsByPerson(Long id);
}
