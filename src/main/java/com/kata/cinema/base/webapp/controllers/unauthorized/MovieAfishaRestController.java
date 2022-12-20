package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.dto.MovieDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/afisha")
public class MovieAfishaRestController {

    private final MovieDtoService movieDtoService;

    public MovieAfishaRestController(MovieDtoService movieDtoService) {
        this.movieDtoService = movieDtoService;
    }

    @GetMapping("")
    public ResponseEntity<PageDto<MovieResponseDto>> getAfisha(@RequestParam(required = false) Long genreId) {
        PageDto<MovieResponseDto> moviesAfisha = movieDtoService.getMoviesAfishaByGenre(genreId);
        return new ResponseEntity<>(moviesAfisha, HttpStatus.OK);
    }
}
