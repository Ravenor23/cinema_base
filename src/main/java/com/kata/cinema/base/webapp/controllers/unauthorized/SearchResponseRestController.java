package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import com.kata.cinema.base.service.dto.impl.SearchResponseService;
import org.springframework.web.bind.annotation.*;


@RestController
public class SearchResponseRestController {

    private final SearchResponseService searchResponseService;

    public SearchResponseRestController(SearchResponseService searchResponseService) {
        this.searchResponseService = searchResponseService;
    }

    @GetMapping("/api/search")
    public SearchResponseDto searchResponseDtoList(@RequestParam("filterPattern") String name){
        return searchResponseService.searchResponseDtoList(name);
    }
}