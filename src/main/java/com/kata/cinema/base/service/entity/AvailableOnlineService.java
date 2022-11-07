package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.AvailableOnlineMovie;

import java.util.List;

public interface AvailableOnlineService {

    void save(AvailableOnlineMovieRequestDto availableOnlineMovieDto, Long movieId);

    void activate(Long movieId);

    void deactivate(Long movieId);

    List<AvailableOnlineMovie> getAll();
}
