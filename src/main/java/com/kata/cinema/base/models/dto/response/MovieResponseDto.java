package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieResponseDto {

    private Long id;
    private String name;
    private String originalName;
    private Integer time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateRelease;

    private String countries;
    private String genres;
    private String director;
    private String roles;
    private Long countScore;

    public MovieResponseDto(Long id, String name, String originalName, Integer time, LocalDate dateRelease,
                            String countries, Long countScore) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.time = time;
        this.dateRelease = dateRelease;
        this.countries = countries;
        this.countScore = countScore;
    }

}
