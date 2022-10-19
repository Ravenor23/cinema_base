package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.dto.impl.ExcertionService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class ExcertionController {

    private final ExcertionService excertionService;

    public ExcertionController(ExcertionService excertionService) {
        this.excertionService = excertionService;
    }

    @GetMapping("/api/movies/{id}/excertions/page/{pageNumber}?itemsOnPage=")
    public PageDto<ExcertionResponseDto> findExcertionPageDtoByIdFromMovies(@PathVariable("id") Long id,
                                                                            @PathVariable PageDto pageNumber) {
        return excertionService.findExcertionPageDtoById(id);
    }

    @GetMapping("/api/persons/{id}/excertions/page/{pageNumber}?itemsOnPage=")
    public PageDto<ExcertionResponseDto> findExcertionPageDtoByIdFromPerson(@PathVariable("id") Long id,
                                                                            @PathVariable PageDto pageNumber) {
        return excertionService.findExcertionPageDtoById(id);
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
                                @RequestBody ExcertionRequestDto excertionRequestDto) {
        excertionService.updateExcertion(id, excertionRequestDto);
    }

    @DeleteMapping("/api/excertions/{id}")
    public void deleteExcertion(@PathVariable Long id) {
        excertionService.deleteExcertion(id);
    }
}
