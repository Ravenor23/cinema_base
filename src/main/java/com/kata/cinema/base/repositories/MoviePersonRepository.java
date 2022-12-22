package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviePersonRepository extends JpaRepository<MoviePerson, Long> {

    @Query("from MoviePerson WHERE professions.id = :professionId")
    List<MoviePerson> getByProfessionId(long professionId);
}
