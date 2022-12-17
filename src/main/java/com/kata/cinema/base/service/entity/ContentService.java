package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.ContentResponseDto;
import com.kata.cinema.base.models.entity.Content;

import java.io.File;
import java.util.List;

public interface ContentService {
    void save(Content content);

    void saveFile(Long id, File file);

    List<ContentResponseDto> getContentByMovieId(Long movieId);

    List<ContentResponseDto> getContentByPersonId(Long personId);
}
