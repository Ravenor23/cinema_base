package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.mappers.AvailableOnlineRequestMapper;
import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.AvailableOnlineMovie;
import com.kata.cinema.base.repositories.AvailableOnlineMovieRepository;
import com.kata.cinema.base.service.entity.AvailableOnlineService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableOnlineMovieImpl implements AvailableOnlineService {

    private final AvailableOnlineMovieRepository availableOnlineMovieRepository;
    private final MovieService movieService;
    private final AvailableOnlineRequestMapper availableOnlineRequestMapper;

    public AvailableOnlineMovieImpl(AvailableOnlineMovieRepository availableOnlineMovieRepository,
                                    MovieService movieService, AvailableOnlineRequestMapper availableOnlineRequestMapper) {
        this.availableOnlineMovieRepository = availableOnlineMovieRepository;
        this.movieService = movieService;
        this.availableOnlineRequestMapper = availableOnlineRequestMapper;
    }

    @Override
    public void save(AvailableOnlineMovieRequestDto availableOnlineMovieDto, Long movieId) {
        if ((availableOnlineMovieDto.getAvailablePlus() == null) &&
                (availableOnlineMovieDto.getBuyPrice() == null) ||
                (availableOnlineMovieDto.getAvailablePlus() == null) &&
                        (availableOnlineMovieDto.getRentalPrice() == null) ||
                ((availableOnlineMovieDto.getRentalPrice() == null) &&
                        (availableOnlineMovieDto.getBuyPrice() == null)) &&
                        (availableOnlineMovieDto.getAvailablePlus() == null)) {
            throw new NullPointerException();
        }
        AvailableOnlineMovie availableOnlineMovie =
                availableOnlineRequestMapper.toEntity(availableOnlineMovieDto);
        availableOnlineMovie.setMovie(movieService.findById(movieId));

        availableOnlineMovieRepository.save(availableOnlineMovie);
    }

    @Override
    public void activate(Long movieId) {
        AvailableOnlineMovie availableOnlineMovie =
                availableOnlineMovieRepository.
                        findAvailableOnlineMovieByMovie(movieService.findById(movieId));
        availableOnlineMovie.setEnabled(true);

        availableOnlineMovieRepository.save(availableOnlineMovie);
    }

    @Override
    public void deactivate(Long movieId) {
        AvailableOnlineMovie availableOnlineMovie =
                availableOnlineMovieRepository.
                        findAvailableOnlineMovieByMovie(movieService.findById(movieId));
        availableOnlineMovie.setEnabled(false);
        availableOnlineMovieRepository.save(availableOnlineMovie);
    }

    @Override
    public List<AvailableOnlineMovie> getAll() {
        return availableOnlineMovieRepository.findAll();
    }
}
