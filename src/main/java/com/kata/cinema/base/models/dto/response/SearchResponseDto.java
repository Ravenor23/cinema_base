package com.kata.cinema.base.models.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {
    private List<SearchMovieDto> searchMovieDto;// - limit = 3
    private List<SearchCollectionDto> searchCollectionDto;// limit = 3
    private List<SearchPersonDto> searchPersonDto;// limit = 3
}
