package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Score;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScoreUserMapper {
    List<ScoreMovieResponseDto> modelsToDTO (List<Score> scores);

    Score toEntity (ScoreMovieResponseDto scoreMovieResponseDto);

    ScoreMovieResponseDto toDTO (Score score);
}
