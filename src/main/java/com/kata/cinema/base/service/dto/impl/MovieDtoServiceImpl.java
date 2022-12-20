package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.MovieResponseDtoMapper;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.MoviePerson;
import com.kata.cinema.base.models.entity.MovieTicket;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.models.enums.TypeCharacter;
import com.kata.cinema.base.repositories.MoviePersonRepository;
import com.kata.cinema.base.repositories.MovieTicketRepository;
import com.kata.cinema.base.repositories.ScoreRepository;
import com.kata.cinema.base.service.dto.MovieDtoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieDtoServiceImpl implements MovieDtoService {

    private final MovieTicketRepository movieTicketRepository;
    private final ScoreRepository scoreRepository;
    private final MoviePersonRepository moviePersonRepository;
    private final MovieResponseDtoMapper movieResponseDtoMapper;

    public MovieDtoServiceImpl(MovieTicketRepository movieTicketRepository,
                               MovieResponseDtoMapper movieResponseDtoMapper, ScoreRepository scoreRepository,
                               MoviePersonRepository moviePersonRepository) {
        this.movieTicketRepository = movieTicketRepository;
        this.movieResponseDtoMapper = movieResponseDtoMapper;
        this.scoreRepository = scoreRepository;
        this.moviePersonRepository = moviePersonRepository;
    }

    @Override
    @Transactional
    public PageDto<MovieResponseDto> getMoviesAfishaByGenre(Long genreId) {

        int countScore = 0;
        int countAmount = 0;
        List<MovieTicket> movieTicket = movieTicketRepository.findAllAndOrderByEndShowDate();
        List<Score> allScore = scoreRepository.findAll();
        List<MoviePerson> allMoviePerson = moviePersonRepository.findAll();
        List<MovieTicket> movieTicketResp = new ArrayList<>();

        for (MovieTicket ticket : movieTicket) {
            Set<Genre> genres = ticket.getMovie().getGenres();
            for (Genre genre : genres) {
                if (genre.getId().equals(genreId)) {
                    movieTicketResp.add(ticket);
                }
            }
        }
        List<MovieResponseDto> movieResponseDtoList = new ArrayList<>();

        for (MovieTicket ticket : movieTicketResp) {

            movieResponseDtoList.add(movieResponseDtoMapper.toDto(ticket.getMovie()));
        }

        for (MovieResponseDto movieResponseDto : movieResponseDtoList) {

            for (Score score : allScore) {
                if (movieResponseDto.getId().equals(score.getMovie().getId())) {
                    countScore += score.getScore();
                    countAmount += 1;
                }
            }
            if (countAmount != 0) {
                movieResponseDto.setCountScore(countScore / countAmount);
            }
            countScore = 0;
            countAmount = 0;
        }

        for (MovieResponseDto movieResponseDto : movieResponseDtoList) {

            for (MoviePerson moviePerson : allMoviePerson) {
                if (movieResponseDto.getId().equals(moviePerson.getMovie().getId())) {
                    if (moviePerson.getProfessions().getName().equals("director")) {
                        movieResponseDto.setDirector(
                            moviePerson.getPerson().getFirstName() + " " + moviePerson.getPerson().getLastName());
                    }
                    if (moviePerson.getType().equals(TypeCharacter.MAIN_CHARACTER)) {
                        movieResponseDto.setRoles(moviePerson.getNameRole());
                    }
                }
            }
        }
        return new PageDto<>((long) movieResponseDtoList.size(), movieResponseDtoList);
    }
}
