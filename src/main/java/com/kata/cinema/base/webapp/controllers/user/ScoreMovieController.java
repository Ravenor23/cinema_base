package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.ScorePage;
import com.kata.cinema.base.service.dto.impl.ScoreMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ScoreMovieController {

    private final ScoreMovieService scoreMovieService;

    private final ScorePage scorePage;

    @Autowired
    public ScoreMovieController(ScoreMovieService scoreMovieService, ScorePage scorePage) {
        this.scoreMovieService = scoreMovieService;
        this.scorePage = scorePage;
    }

    //TODO Добавить id пользователя
    //@GetMapping("/api/user/scores/page/{pageNumber}")
    //public PageDto<ScoreMovieResponseDto> getScoreMovie(@PathVariable("id") Long id, @PathVariable("pageNumber") Integer pageNumber,
                                                         //                                                    @RequestParam("itemsOnPage") Integer itemsOnPage,
                                                         //                                                    @RequestParam(value = "sortScoreType", defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        //    List<ScoreMovieResponseDto> scores = scoreMovieService.getAllScoreMovie();
        //    HashMap<String, Object> parameters = new HashMap<>();
        //    parameters.put("sortScoreType", sortScoreType);
        //    PageDto<ScoreMovieResponseDto> pageDto = scorePage.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
        //    pageDto.setEntities(scores);
        //    return pageDto;
        //}

    @PostMapping("/api/user/movies/{id}/score")
    public void createScoreMovie (@RequestBody ScoreMovieResponseDto scoreMovieResponseDto) {
        try {
            scoreMovieService.createScoreMovie(scoreMovieResponseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/api/user/movies/{id}/score")
    public void updateScoreMovie(@PathVariable long id,@RequestBody ScoreMovieResponseDto scoreMovieResponseDto) throws Exception {
        Score updateScore = scoreMovieService.findScoreMovieById(id);
        updateScore.setScore(scoreMovieResponseDto.getScore());
        scoreMovieService.createScoreMovie(scoreMovieResponseDto);
    }

    @DeleteMapping("/api/user/scores/{id}")
    public void delete(@PathVariable("id") Long id) {
        scoreMovieService.deleteById(id);
    }
}
