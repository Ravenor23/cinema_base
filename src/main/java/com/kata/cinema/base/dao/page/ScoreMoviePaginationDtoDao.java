package com.kata.cinema.base.dao.page;

import com.kata.cinema.base.dao.PaginationDtoDao;
import com.kata.cinema.base.mappers.ScoreUserMapper;
import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class ScoreMoviePaginationDtoDao implements PaginationDtoDao<ScoreMovieResponseDto> {

    private final ScoreUserMapper scoreUserMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ScoreMoviePaginationDtoDao(ScoreUserMapper scoreUserMapper) {
        this.scoreUserMapper = scoreUserMapper;
    }

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        List<ScoreMovieResponseDto> scoreMovieResponseDto = scoreUserMapper.modelsToDTO(
                entityManager.createQuery("select s from Score s"
                + "left join fetch s.user u "
                + " where s.scores =: scores")
                        .setParameter("scores", parameters.get("scores"))
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
