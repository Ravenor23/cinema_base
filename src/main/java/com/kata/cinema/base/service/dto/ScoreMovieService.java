package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;

import java.util.List;

public interface ScoreMovieService {


    void createScoreMovie(ScoreMovieResponseDto scoreMovieResponseDto) throws Exception;

    Score findScoreMovieById(Long id);

    void deleteById(Long id);
}
