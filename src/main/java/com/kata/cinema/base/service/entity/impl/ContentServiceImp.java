package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.mappers.ContentMapper;
import com.kata.cinema.base.models.dto.response.ContentResponseDto;
import com.kata.cinema.base.models.entity.Content;
import com.kata.cinema.base.repositories.ContentRepository;
import com.kata.cinema.base.repositories.MovieRepository;
import com.kata.cinema.base.service.entity.ContentService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ContentServiceImp implements ContentService {

    private final ContentRepository contentRepository;
    private final MovieRepository movieRepository;
    private final ContentMapper contentMapper;

    public ContentServiceImp(ContentRepository contentRepository, MovieRepository movieRepository,
                             ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.movieRepository = movieRepository;
        this.contentMapper = contentMapper;
    }

    @Override
    public List<ContentResponseDto> getContentByMovieId(Long movieId) {
        List<Content> contentByMovieId = contentRepository.getContentByMovieId(movieId);
        if (contentByMovieId.isEmpty()) {
            throw new NoSuchElementException();
        }
        return contentMapper.modelsToDTO(contentByMovieId);
    }

    @Override
    public List<ContentResponseDto> getContentByPersonId(Long personId) {
        List<Content> contentByPersonId = contentRepository.getContentByPersonId(personId);
        if (contentByPersonId.isEmpty()) {
            throw new NoSuchElementException();
        }
        return contentMapper.modelsToDTO(contentByPersonId);
    }

    @Override
    public void save(Content content) {
        contentRepository.save(content);
    }

    @Override
    public void saveFile(Long id, File file) {
        Content content = new Content();
        content.setMovie(movieRepository.getById(id));
        content.setContentUrl(file.toString());
        contentRepository.save(content);
    }
}
