package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.entity.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {

    void createChapter(String chapterName);

    void deleteChapter(Long id);

    void updateChapter(Long id, String newName);

    List<ChapterResponseDto> getAllChapters();

    Chapter getById(Long id);

    Chapter getByName(String name);

    Optional<Chapter> getOptionalById(Long id);

}
