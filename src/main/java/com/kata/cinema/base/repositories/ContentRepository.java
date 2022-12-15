package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("from Content WHERE movie.id=:movieId")
    List<Content> getContentByMovieId(Long movieId);

    @Query("from Content WHERE person.id=:personId")
    List<Content> getContentByPersonId(Long personId);
}
