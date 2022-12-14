package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.entity.MovieTicket;
import com.kata.cinema.base.repositories.MovieRepository;
import com.kata.cinema.base.repositories.MovieTicketRepository;
import com.kata.cinema.base.service.entity.MovieTicketService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieTicketServiceImpl implements MovieTicketService {

    private final MovieTicketRepository movieTicketRepository;
    private final MovieRepository movieRepository;
    private final LocalDate todayDate;

    public MovieTicketServiceImpl(MovieTicketRepository movieTicketRepository, MovieRepository movieRepository) {
        this.movieTicketRepository = movieTicketRepository;
        this.movieRepository = movieRepository;
        this.todayDate = LocalDate.now();
    }

    @Override
    @Transactional
    public void save() {
        List<Movie> movieByDataRelease = movieRepository.findByDataRelease(todayDate);
        if (!movieByDataRelease.isEmpty()) {
            for (Movie movie : movieByDataRelease) {
                MovieTicket movieTicket = new MovieTicket();
                movieTicket.setMovie(movie);
                movieTicket.setEndShowDate(todayDate.plusMonths(1));
                movieTicketRepository.save(movieTicket);
            }
        }
    }

    @Override
    @Transactional
    public void delete() {
        movieTicketRepository.deleteByEndShowDate(todayDate);
    }
}
