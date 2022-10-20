package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.ScorePaginationDtoService;
import com.kata.cinema.base.service.dto.impl.ScoreMovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ScoreMovieController {

    private final ScoreMovieServiceImpl scoreMovieServiceImpl;

    private final ScorePaginationDtoService scorePaginationDtoService;

    @Autowired
    public ScoreMovieController(ScoreMovieServiceImpl scoreMovieServiceImpl, ScorePaginationDtoService scorePaginationDtoService) {
        this.scoreMovieServiceImpl = scoreMovieServiceImpl;
        this.scorePaginationDtoService = scorePaginationDtoService;
    }

    @GetMapping("/api/user/scores/page/{pageNumber}")
    public PageDto<ScoreMovieResponseDto> getScoreMovie(@RequestParam(name = "id", defaultValue = "1") Long id,   @PathVariable("pageNumber") Integer pageNumber,
                                                        @RequestParam("itemsOnPage") Integer itemsOnPage,
                                                        @RequestParam(value = "sortScoreType", defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("sortScoreType", sortScoreType);
        return scorePaginationDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }

    @PostMapping("/api/user/movies/{id}/score")
    public void createScoreMovie(@RequestBody ScoreMovieResponseDto scoreMovieResponseDto) {
        try {
            scoreMovieServiceImpl.createScoreMovie(scoreMovieResponseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/api/user/movies/{id}/score")
    public void updateScoreMovie(@PathVariable long id, @RequestBody ScoreMovieResponseDto scoreMovieResponseDto) throws Exception {
        Score updateScore = scoreMovieServiceImpl.findScoreMovieById(id);
        updateScore.setScore(scoreMovieResponseDto.getScore());
        scoreMovieServiceImpl.createScoreMovie(scoreMovieResponseDto);
    }

    @DeleteMapping("/api/user/scores/{id}")
    public void delete(@PathVariable("id") Long id) {
        scoreMovieServiceImpl.deleteById(id);
    }
}
