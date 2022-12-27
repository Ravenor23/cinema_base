package com.kata.cinema.base.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreMovieDto {
    @JsonIgnore
    private Long movieId;

    private String genres;
}
