package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.GenreDtoMapper;
import com.kata.cinema.base.models.dto.response.DirectorMovieDto;
import com.kata.cinema.base.models.dto.response.GenreMovieDto;
import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.RoleMovieDto;
import com.kata.cinema.base.models.entity.Movie;
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
    private final GenreDtoMapper genreDtoMapper;

    public MovieDtoServiceImpl(MovieTicketRepository movieTicketRepository,
                               MovieRepository movieRepository,
                               MoviePersonRepository moviePersonRepository,
                               GenreDtoMapper genreDtoMapper) {
        this.movieTicketRepository = movieTicketRepository;
        this.moviePersonRepository = moviePersonRepository;
        this.movieRepository = movieRepository;
        this.genreDtoMapper = genreDtoMapper;
    }

    @Override
    @Transactional
    public PageDto<MovieResponseDto> getMoviesAfishaByGenre(Long genreId) {
        List<MovieResponseDto> movieTicket = movieTicketRepository.findAllAndOrderByEndShowDate(genreId);

        List<GenreMovieDto> movieResponseDtoList = new ArrayList<>();

        List<Long> movieResponseIdList = new ArrayList<>();

        for (MovieResponseDto movieResponseDto : movieTicket) {

            movieResponseIdList.add(movieResponseDto.getId());
        }

        List<Movie> moviesById = movieRepository.getMoviesById(movieResponseIdList);

        for (Movie movie : moviesById) {
            movieResponseDtoList.add(genreDtoMapper.toDto(movie));
        }

        for (MovieResponseDto responseDto : movieTicket) {

            for (GenreMovieDto genreMovieDto : movieResponseDtoList) {
                if (responseDto.getId().equals(genreMovieDto.getMovieId())) {
                    responseDto.setGenres(genreMovieDto.getGenres());
                }
            }
        }

        List<RoleMovieDto> roleMovieDtos = moviePersonRepository.getRoles(movieResponseIdList);

        for (MovieResponseDto responseDto : movieTicket) {

            for (RoleMovieDto roleMovieDto : roleMovieDtos) {
                if (responseDto.getId().equals(roleMovieDto.getMovieId())) {
                    responseDto.setRoles(roleMovieDto.getName());
                }
            }
        }

        List<DirectorMovieDto> directorMovieDtos = moviePersonRepository.getDirectorName(movieResponseIdList);

        for (MovieResponseDto responseDto : movieTicket) {

            for (DirectorMovieDto directorMovieDto : directorMovieDtos) {
                if (responseDto.getId().equals(directorMovieDto.getMovieId())) {
                    responseDto.setDirector(directorMovieDto.getName());
                }
            }
        }

        return new PageDto<>((long) movieTicket.size(), movieTicket);
    }
}
