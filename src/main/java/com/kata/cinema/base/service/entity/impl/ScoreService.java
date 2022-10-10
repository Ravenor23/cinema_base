package src.src.main.java.com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.ScoreResponseDto;

public interface ScoreService {
    void save(ScoreResponseDto scoreResponseDto);
    ScoreResponseDto findById(Long id);

    void deleteById(Long id);
}
