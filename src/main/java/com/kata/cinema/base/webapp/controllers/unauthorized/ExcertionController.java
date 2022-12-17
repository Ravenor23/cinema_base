package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.dto.ExcertionMoviePaginationDtoService;
import com.kata.cinema.base.service.dto.ExcertionPersonPaginationDtoService;
import com.kata.cinema.base.service.dto.impl.ExcertionServiceImpl;
import java.util.HashMap;
import java.util.Map;
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

    private final ExcertionServiceImpl excertionService;
    private final ExcertionMoviePaginationDtoService excertionMoviePaginationService;
    private final ExcertionPersonPaginationDtoService excertionPersonPaginationService;

    public ExcertionController(ExcertionServiceImpl excertionService,
        ExcertionMoviePaginationDtoService excertionMoviePaginationService,
        ExcertionPersonPaginationDtoService excertionPersonPaginationService) {
        this.excertionService = excertionService;
        this.excertionMoviePaginationService = excertionMoviePaginationService;
        this.excertionPersonPaginationService = excertionPersonPaginationService;
    }


    @GetMapping("/api/movies/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getExcertionFromMovies(@PathVariable("id") Long id,
                                                                @PathVariable("pageNumber") Integer currentPage,
                                                                @RequestParam("itemsOnPage") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movie", id);
        return excertionMoviePaginationService.getPageDtoWithParameters(currentPage, itemsOnPage,parameters);
    }

    @GetMapping("/api/person/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getExcertionFromPerson(@PathVariable("id") Long id,
                                                                @PathVariable("pageNumber") Integer currentPage,
                                                                @RequestParam("itemsOnPage") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", id);
        return excertionPersonPaginationService.getPageDtoWithParameters(currentPage, itemsOnPage,parameters);
    }

    @PostMapping("/api/movies/{id}/excertions")
    public void saveExcertionFromMovies(@PathVariable Long id,
                                        @RequestBody ExcertionRequestDto excertionRequestDto) {
        excertionService.saveExcertion(excertionRequestDto);
    }

    @PostMapping("/api/persons/{id}/excertions")
    public void saveExcertionFromPerson(@PathVariable Long id,
                                        @RequestBody ExcertionRequestDto excertionRequestDto) {
        excertionService.saveExcertion(excertionRequestDto);
    }

    @PutMapping("/api/excertions/{id}")
    public void updateExcertion(@PathVariable("id") Long id,
                                @RequestBody ExcertionRequestDto excertionRequestDto) throws Exception {
        excertionService.updateExcertion(id, excertionRequestDto);
    }

    @DeleteMapping("/api/excertions/{id}")
    public void deleteExcertion(@PathVariable Long id) {
        excertionService.deleteExcertion(id);
    }
}
