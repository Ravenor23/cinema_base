package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.exceptions.NoScoreException;
import com.kata.cinema.base.exceptions.ScoreAlreadyExistsException;
import com.kata.cinema.base.exceptions.WrongUserScoreException;
import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.models.entity.User;
import com.kata.cinema.base.repositories.ScoreMovieRepository;
import com.kata.cinema.base.service.dto.ScoreMovieService;
import com.kata.cinema.base.service.dto.ScorePaginationDtoService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ScoreMovieServiceImpl implements ScoreMovieService {

    private final UserService userService;
    private final ScoreMovieRepository scoreMovieRepository;
    private final MovieService movieService;
    private final ScorePaginationDtoService scorePaginationDtoService;

    @Autowired
    public ScoreMovieServiceImpl(UserService userService,
                                 ScoreMovieRepository scoreMovieRepository,
                                 MovieService movieService, ScorePaginationDtoService scorePaginationDtoService) {
        this.userService = userService;
        this.scoreMovieRepository = scoreMovieRepository;
        this.movieService = movieService;
        this.scorePaginationDtoService = scorePaginationDtoService;
    }

    @Override
    @Transactional
    public void createScoreMovie(Integer score, Long movieId, String userName) {
        User user = userService.getUserByEmail(userName);
        if (scoreMovieRepository.findScoreByMovieIdAndUserId(movieId, user.getId()).isPresent()) {
            throw new ScoreAlreadyExistsException();
        }
        Score scoreEntity = new Score();
        scoreEntity.setScore(score);
        scoreEntity.setMovie(movieService.findById(movieId));
        scoreEntity.setUser(user);
        scoreEntity.setDate(LocalDate.now());
        scoreMovieRepository.save(scoreEntity);
    }

    @Override
    public Score findScoreMovieById(Long id) {
        return scoreMovieRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public void updateScoreMovie(Integer score, Long movieId, String userName) {
        Score scoreEntity = getScoreByMovieIdAndUserName(movieId, userName);
        scoreEntity.setScore(score);
        scoreEntity.setDate(LocalDate.now());
        scoreMovieRepository.save(scoreEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long scoreId, String userName) {
        Score score = scoreMovieRepository.findById(scoreId).orElseThrow(() -> new NoScoreException(scoreId));
        if (!userService.getUserByEmail(userName).getId().equals(score.getUser().getId())) {
            throw new WrongUserScoreException();
        }
        scoreMovieRepository.deleteById(scoreId);
    }

    @Override
    public PageDto<ScoreMovieResponseDto> getScoreMoviePageDto(Integer currentPage,
                                                               Integer itemsOnPage,
                                                               Map<String, Object> parameters) {
        Long userId = userService.getUserByEmail((String) parameters.get("user")).getId();
        parameters.put("user", userId);
        return scorePaginationDtoService.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }

    @Override
    public List<Score> getAll() {
        return scoreMovieRepository.findAll();
    }

    @Override
    public Optional<Score> findOptionalById(Long id) {
        return scoreMovieRepository.findById(id);
    }

    public Score getScoreByMovieIdAndUserName(Long movieId, String userName) {
        User user = userService.getUserByEmail(userName);
        return scoreMovieRepository.findScoreByMovieIdAndUserId(movieId, user.getId())
                .orElseThrow(() -> new NoScoreException(movieId));
    }
}
