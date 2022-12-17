package com.kata.cinema.base.models.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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


