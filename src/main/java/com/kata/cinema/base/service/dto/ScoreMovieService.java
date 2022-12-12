package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScoreMovieService {


    void createScoreMovie(Integer score, Long movieId, String userName) throws Exception;

    Score findScoreMovieById(Long id);

    void updateScoreMovie(Integer score, Long movieId, String userName);

    void deleteById(Long scoreId, String userName);

    PageDto<ScoreMovieResponseDto> getScoreMoviePageDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters);

    List<Score> getAll();

    Optional<Score> findOptionalById(Long id);
}
