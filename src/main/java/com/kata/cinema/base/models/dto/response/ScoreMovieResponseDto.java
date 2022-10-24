package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreMovieResponseDto {
    private Long id;
    private Integer score;
    private LocalDate date;
    private Integer time;
    private Long movieId;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private Double avgScore;
    private Long countScore;
}


