package com.kata.cinema.base.dao.page;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class ScoreMoviePaginationDtoDao implements PaginationDtoDao<ScoreMovieResponseDto> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return null;
    }
}
