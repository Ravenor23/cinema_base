package com.kata.cinema.base.models.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {
    private List<SearchMovieDto> searchMovieDto;
    private List<SearchCollectionDto> searchCollectionDto;
    private List<SearchPersonDto> searchPersonDto;
}
