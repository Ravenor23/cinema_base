package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.ScoreResponseDto;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.repositories.MovieRepository;
import com.kata.cinema.base.repositories.ScoreRepository;
import com.kata.cinema.base.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImp implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    public ScoreServiceImp(ScoreRepository scoreRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(ScoreResponseDto scoreResponseDto) {
        Score score = new Score();
        score.setMovie(movieRepository.findById(scoreResponseDto.getMovieId()).orElseThrow());
        score.setUser(userRepository.getById(scoreResponseDto.getUserId()));
        score.setScore(scoreResponseDto.getScore());
        scoreRepository.save(score);
    }

    @Override
    public ScoreResponseDto findById(Long id) {
        Score score = scoreRepository.findById(id).orElseThrow(RuntimeException::new);
        ScoreResponseDto scopeResponseDto = ScoreResponseDto.builder()
                .id(score.getId())
                .movieId(score.getMovie().getId())
                .score(Integer.parseInt(String.valueOf(score.getScore())))
                .build();
        return scopeResponseDto;
    }

    @Override
    public void deleteById(Long id) {
        scoreRepository.deleteById(id);
    }

}