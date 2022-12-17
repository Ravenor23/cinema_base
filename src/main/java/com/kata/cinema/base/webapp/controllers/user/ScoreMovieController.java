package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.dto.ScoreMovieService;
import com.kata.cinema.base.service.dto.impl.ScoreMovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;

@RestController
public class ScoreMovieController {
    private final ScoreMovieService scoreMovieService;

    @Autowired
    public ScoreMovieController(ScoreMovieServiceImpl scoreMovieService) {
        this.scoreMovieService = scoreMovieService;

    }

    @GetMapping("/api/user/scores/page/{pageNumber}")
    public PageDto<ScoreMovieResponseDto> getScoreMovie(Principal principal, @PathVariable("pageNumber") Integer pageNumber,
                                                        @RequestParam("itemsOnPage") Integer itemsOnPage,
                                                        @RequestParam(value = "sortScoreType", defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("sortScoreType", sortScoreType);
        parameters.put("user", principal.getName());
        return scoreMovieService.getScoreMoviePageDto(pageNumber, itemsOnPage, parameters);
    }

    @PostMapping("/api/user/movies/{id}/score")
    public void createScoreMovie(Principal principal,
                                 @PathVariable("id") Long movieId,
                                 @RequestParam("score") Integer score) throws Exception {
        scoreMovieService.createScoreMovie(score, movieId, principal.getName());
    }

    @PatchMapping("/api/user/movies/{id}/score")
    public void updateScoreMovie(Principal principal,
                                 @PathVariable("id") Long movieId,
                                 @RequestParam("score") Integer score) {
        scoreMovieService.updateScoreMovie(score, movieId, principal.getName());
    }

    @DeleteMapping("/api/user/scores/{id}")
    public void delete(@PathVariable("id") Long scoreId, Principal principal) {
        scoreMovieService.deleteById(scoreId, principal.getName());
    }
}
