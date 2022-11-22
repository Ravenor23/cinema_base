package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ChapterResponseMapper;
import com.kata.cinema.base.models.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.entity.Chapter;
import com.kata.cinema.base.repositories.ChapterRepository;
import com.kata.cinema.base.service.dto.ChapterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    private final ChapterResponseMapper chapterResponseMapper;
    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterResponseMapper chapterResponseMapper, ChapterRepository chapterRepository) {
        this.chapterResponseMapper = chapterResponseMapper;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public void createChapter(String chapterName) {
        Chapter chapter = new Chapter();
        chapter.setName(chapterName);
        chapterRepository.save(chapter);
    }

    @Override
    public void deleteChapter(Long id) {
            chapterRepository.deleteById(id);
    }

    @Override
    public void updateChapter(Long id, String newName) {
        Chapter chapter = chapterRepository.getById(id);
        chapter.setName(newName);
        chapterRepository.save(chapter);
    }

    @Override
    public List<ChapterResponseDto> getAllChapters() {
        return chapterResponseMapper.modelsToDTO(chapterRepository.findAll());
    }

    @Override
    public Chapter getById(Long id) {
        return chapterRepository.getById(id);
    }

    @Override
    public Chapter getByName(String name) {
        return chapterRepository.findChapterByName(name);
    }
}
