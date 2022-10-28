package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.mappers.ScoreUserMapper;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.repositories.pagination.ScoreMoviePaginationDtoDao;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreMoviePaginationDtoDaoImpl implements ScoreMoviePaginationDtoDao {

    private final ScoreUserMapper scoreUserMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ScoreMoviePaginationDtoDaoImpl(ScoreUserMapper scoreUserMapper) {
        this.scoreUserMapper = scoreUserMapper;
    }

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        List<ScoreMovieResponseDto> scoreMovieResponseDto = scoreUserMapper.modelsToDTO(
                entityManager.createQuery("select s from Score s"
                + " where s.user =: user")
                        .setParameter("user", parameters.get("user"))
                        .setFirstResult((currentPage - 1) * itemsOnPage)
                        .setMaxResults(itemsOnPage)
                        .getResultList());
                return scoreMovieResponseDto;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (Long) entityManager.createQuery("SELECT COUNT (s) FROM Score s").getSingleResult();
    }
}
