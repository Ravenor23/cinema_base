package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Profession;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionRepositories extends JpaRepository<Profession, Long> {

  @Query("select p.professions from Person p where p.id = ?1 ")
  Set<Profession> getProfessionsByPerson(Long id);
}
