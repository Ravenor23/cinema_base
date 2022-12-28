package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.dto.response.DirectorMovieDto;
import com.kata.cinema.base.models.dto.response.GenreMovieDto;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.RoleMovieDto;
import com.kata.cinema.base.repositories.MoviePersonRepository;
import com.kata.cinema.base.repositories.MovieRepository;
import com.kata.cinema.base.repositories.MovieTicketRepository;
import com.kata.cinema.base.service.dto.MovieDtoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieDtoServiceImpl implements MovieDtoService {

    private final MovieTicketRepository movieTicketRepository;
    private final MoviePersonRepository moviePersonRepository;
    private final MovieRepository movieRepository;

    public MovieDtoServiceImpl(MovieTicketRepository movieTicketRepository,
                               MovieRepository movieRepository,
                               MoviePersonRepository moviePersonRepository) {
        this.movieTicketRepository = movieTicketRepository;
        this.moviePersonRepository = moviePersonRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public PageDto<MovieResponseDto> getMoviesAfishaByGenre(Long genreId) {

        List<MovieResponseDto> movieTicket = movieTicketRepository.findAllAndOrderByEndShowDate(genreId);

        List<Long> movieResponseIdList = new ArrayList<>();

        for (MovieResponseDto movieResponseDto : movieTicket) {

            movieResponseIdList.add(movieResponseDto.getId());
        }

        List<GenreMovieDto> moviesGenreById = movieRepository.getMoviesById(movieResponseIdList);

        List<RoleMovieDto> roleMovieDtos = moviePersonRepository.getRoles(movieResponseIdList);

        List<DirectorMovieDto> directorMovieDtos = moviePersonRepository.getDirectorName(movieResponseIdList);

        for (MovieResponseDto responseDto : movieTicket) {
            Long movieId = responseDto.getId();
            for (GenreMovieDto genreMovieDto : moviesGenreById) {
                if (movieId.equals(genreMovieDto.getMovieId())) {
                    if (responseDto.getGenres() == null) {
                        responseDto.setGenres(genreMovieDto.getGenres() + ", ");
                    } else {
                        responseDto.setGenres(responseDto.getGenres() + genreMovieDto.getGenres() + ", ");
                    }
                }
            }
            responseDto.setGenres(responseDto.getGenres().substring(0, responseDto.getGenres().length() - 2));

            for (RoleMovieDto roleMovieDto : roleMovieDtos) {
                if (movieId.equals(roleMovieDto.getMovieId())) {
                    responseDto.setRoles(roleMovieDto.getName());
                }
            }

            for (DirectorMovieDto directorMovieDto : directorMovieDtos) {
                if (movieId.equals(directorMovieDto.getMovieId())) {
                    responseDto.setDirector(directorMovieDto.getName());
                }
            }
        }

        return new PageDto<>((long) movieTicket.size(), movieTicket);
    }
}
