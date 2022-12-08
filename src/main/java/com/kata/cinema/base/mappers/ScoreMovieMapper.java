package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.entity.Score;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScoreMovieMapper {

    default ScoreMovieResponseDto toScoreMovieResponseDto(Score score) {
        Movie movie = score.getMovie();
        ScoreMovieResponseDto scoreMovieResponseDto = new ScoreMovieResponseDto();

        scoreMovieResponseDto.setId(score.getId());
        scoreMovieResponseDto.setScore(score.getScore());
        scoreMovieResponseDto.setDate(score.getDate());
        scoreMovieResponseDto.setTime(movie.getTime());
        scoreMovieResponseDto.setMovieId(movie.getId());
        scoreMovieResponseDto.setName(movie.getName());
        scoreMovieResponseDto.setOriginalName(movie.getOriginName());
        scoreMovieResponseDto.setDateRelease(movie.getDataRelease());

        return scoreMovieResponseDto;
    }


    List<ScoreMovieResponseDto> toScoreMovieResponseDto(List<Score> scores);
}

