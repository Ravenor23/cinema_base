package com.kata.cinema.base.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class AvailableOnlineMovieRequestDto {
    private Integer rentalPrice;
    private Integer buyPrice;
    private Boolean availablePlus;
}
