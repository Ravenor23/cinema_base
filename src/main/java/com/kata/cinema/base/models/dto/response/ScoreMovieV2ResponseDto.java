package com.kata.cinema.base.models.dto.response;

import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreMovieV2ResponseDto {
    private Long id;
    private String time;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private Double avgScore;
    private Long countScore;
}
