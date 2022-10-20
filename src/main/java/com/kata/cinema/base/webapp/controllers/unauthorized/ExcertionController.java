package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.service.PaginationDtoService;
import com.kata.cinema.base.service.dto.impl.ExcertionService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class ExcertionController {

    private final ExcertionService excertionService;
    private final PaginationDtoService paginationDtoService;

    public ExcertionController(ExcertionService excertionService, PaginationDtoService paginationDtoService) {
        this.excertionService = excertionService;
        this.paginationDtoService = paginationDtoService;
    }

    @GetMapping("/api/movies/{id}/excertions/page/{pageNumber}?itemsOnPage=")
    public PageDto<ExcertionResponseDto> findExcertionPageDtoByIdFromMovies(@PathVariable("id") Long id,
                                                                            @PathVariable("pageNumber") Integer currentPage,
                                                                            @RequestParam("itemsOnPage") Integer itemsOnPage) {
        paginationDtoService.getPageDto(currentPage, itemsOnPage);
        return excertionService.findExcertionPageDtoById(id);
    }

    @GetMapping("/api/person/{id}/excertions/page/{pageNumber}?itemsOnPage=")
    public PageDto<ExcertionResponseDto> findExcertionPageDtoByIdFromPerson(@PathVariable("id") Long id,
                                                                            @PathVariable("pageNumber") Integer currentPage,
                                                                            @RequestParam("itemsOnPage") Integer itemsOnPage) {
        paginationDtoService.getPageDto(currentPage, itemsOnPage);
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
