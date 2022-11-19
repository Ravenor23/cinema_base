package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.AvailableOnlineMovie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvailableOnlineRequestMapper {
    AvailableOnlineMovie toEntity(AvailableOnlineMovieRequestDto availableOnlineMovieRequestDto);
}
