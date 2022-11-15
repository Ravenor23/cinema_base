package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Genre;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

  @Override
  List<Genre> findAll();

  @Query("select mp.movie.genres from MoviePerson mp where mp.person.id = ?1")
  Set<Genre> getGenresByPerson(Long id);

}
