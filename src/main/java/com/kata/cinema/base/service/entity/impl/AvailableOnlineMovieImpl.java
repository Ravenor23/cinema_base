package com.kata.cinema.base.service.entity.impl;

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

    public AvailableOnlineMovieImpl(AvailableOnlineMovieRepository availableOnlineMovieRepository,
                                    MovieService movieService) {
        this.availableOnlineMovieRepository = availableOnlineMovieRepository;
        this.movieService = movieService;
    }

    @Override
    public void save(AvailableOnlineMovieRequestDto availableOnlineMovieDto, Long movieId) {
        AvailableOnlineMovie availableOnlineMovie = new AvailableOnlineMovie();
        availableOnlineMovie.setBuyPrice(availableOnlineMovieDto.getBuyPrice());
        availableOnlineMovie.setRentalPrice(availableOnlineMovieDto.getRentalPrice());
        availableOnlineMovie.setAvailablePlus(availableOnlineMovieDto.getAvailablePlus());
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
