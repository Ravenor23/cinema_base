package com.kata.cinema.base.models.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCollectionDto {
    private String name;
    private String url;
    private Integer countMovies;
}
