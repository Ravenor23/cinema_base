package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ScoreUserMapper;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.entity.Score;
import com.kata.cinema.base.repositories.ScoreMovieRepository;
import com.kata.cinema.base.service.dto.ScoreMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScoreMovieServiceImpl implements ScoreMovieService {

    private final ScoreUserMapper scoreUserMapper;

    private final ScoreMovieRepository scoreMovieRepository;

    @Autowired
    public ScoreMovieServiceImpl(ScoreUserMapper scoreUserMapper, ScoreMovieRepository scoreMovieRepository) {
        this.scoreUserMapper = scoreUserMapper;
        this.scoreMovieRepository = scoreMovieRepository;
    }

    @Override
    public void createScoreMovie(ScoreMovieResponseDto scoreMovieResponseDto) throws Exception {
        try {
            scoreMovieRepository.save(scoreUserMapper.toEntity(scoreMovieResponseDto));
        } catch (Exception e) {
            throw new Exception("Оценка уже выставлена");
        }
    }

    @Override
    public Score findScoreMovieById(Long id) {
        return scoreMovieRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteById(Long id) {
        scoreMovieRepository.deleteById(id);
    }
}
