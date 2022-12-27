package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.entity.Excertion;
import com.kata.cinema.base.service.dto.ExcertionDtoService;
import com.kata.cinema.base.service.dto.ExcertionMoviePaginationDtoService;
import com.kata.cinema.base.service.dto.ExcertionPersonPaginationDtoService;
import com.kata.cinema.base.service.dto.impl.ExcertionDtoServiceImpl;
import java.util.HashMap;
import java.util.Map;

import com.kata.cinema.base.service.entity.ExcertionService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class ExcertionController {

    private final ExcertionDtoService excertionDtoService;
    private final PersonService personService;
    private final MovieService movieService;
    private final ExcertionService excertionService;
    private final ExcertionMoviePaginationDtoService excertionMoviePaginationService;
    private final ExcertionPersonPaginationDtoService excertionPersonPaginationService;

    public ExcertionController(ExcertionService excertionService,
                               ExcertionMoviePaginationDtoService excertionMoviePaginationService,
                               ExcertionPersonPaginationDtoService excertionPersonPaginationService,
                               PersonService personService, MovieService movieService,
                               ExcertionDtoService excertionDtoService) {
        this.excertionService = excertionService;
        this.excertionMoviePaginationService = excertionMoviePaginationService;
        this.excertionPersonPaginationService = excertionPersonPaginationService;
        this.movieService = movieService;
        this.personService = personService;
        this.excertionDtoService = excertionDtoService;
    }


    @GetMapping("/api/movies/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getExcertionFromMovies(@PathVariable("id") Long id,
                                                                @PathVariable("pageNumber") Integer currentPage,
                                                                @RequestParam("itemsOnPage") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movie_id", id);
        return excertionMoviePaginationService.getPageDtoWithParameters(currentPage, itemsOnPage,parameters);
    }

    @GetMapping("/api/person/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getExcertionFromPerson(@PathVariable("id") Long id,
                                                                @PathVariable("pageNumber") Integer currentPage,
                                                                @RequestParam("itemsOnPage") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person_id", id);
        return excertionPersonPaginationService.getPageDtoWithParameters(currentPage, itemsOnPage,parameters);
    }

    @PostMapping("/api/movies/{id}/excertions")
    public void saveExcertionFromMovies(@PathVariable Long id,
                                        @RequestBody ExcertionRequestDto excertionRequestDto) {
        Excertion excertion = new Excertion();
        excertion.setDescription(excertionRequestDto.getDescription());
        excertion.setMovie(movieService.getById(id));
        excertionService.save(excertion);
    }

    @PostMapping("/api/persons/{id}/excertions")
    public void saveExcertionFromPerson(@PathVariable Long id,
                                        @RequestBody ExcertionRequestDto excertionRequestDto) {
        Excertion excertion = new Excertion();
        excertion.setDescription(excertionRequestDto.getDescription());
        excertion.setPerson(personService.getById(id));
        excertionService.save(excertion);
    }

    @PutMapping("/api/excertions/{id}")
    public void updateExcertion(@PathVariable("id") Long id,
                                @RequestBody ExcertionRequestDto excertionRequestDto) throws Exception {
        excertionDtoService.updateExcertion(id, excertionRequestDto);
    }

    @DeleteMapping("/api/excertions/{id}")
    public void deleteExcertion(@PathVariable Long id) {
        excertionService.delete(id);
    }
}
