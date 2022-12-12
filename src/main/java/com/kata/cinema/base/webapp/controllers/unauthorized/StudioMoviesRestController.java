package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieV2ResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.dto.StudioMoviesPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudioMoviesRestController {

    private final StudioMoviesPaginationService studioMoviesPaginationService;

    @Autowired
    public StudioMoviesRestController(StudioMoviesPaginationService studioMoviesPaginationService) {
        this.studioMoviesPaginationService = studioMoviesPaginationService;
    }

    @GetMapping("/api/studios/{id}/movies/page/{pageNumber}")
    public PageDto<ScoreMovieV2ResponseDto> getStudioMovies(@PathVariable Long id, @PathVariable Integer pageNumber,
                                                            @RequestParam Integer itemsOnPage,
                                                            @RequestParam(name = "sortScoreType", defaultValue = "SCORE_ASC") SortScoreType sortScoreType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studioId", id);
        parameters.put("sortScoreType", sortScoreType);
        return studioMoviesPaginationService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }
}
