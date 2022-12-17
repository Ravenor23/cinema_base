package com.kata.cinema.base.models.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchMovieDto {
    private Long id;
    private String name;
    private String originalName;
    private String previewUrl;
    private LocalDate date;
    private Integer avgScore;
}
