package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findChapterByName(String name);

}
