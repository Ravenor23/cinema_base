package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.SearchResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchResponseRestController {

    @GetMapping("/api/search?filterPattern=") //GET /api/search?filterPattern= возвращает SearchResponseDto filterPattern - может принимать значения как название фильма, имя персоны, название коллекции
    public List<SearchResponseDto> searchResponseDtoList(){
        return null;
    }
}