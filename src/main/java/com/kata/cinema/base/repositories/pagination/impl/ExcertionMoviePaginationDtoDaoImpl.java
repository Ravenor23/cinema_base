package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.mappers.ExcertionResponseMapper;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.repositories.pagination.ExcertionMoviePaginationDtoDao;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ExcertionMoviePaginationDtoDaoImpl implements ExcertionMoviePaginationDtoDao {

    private final ExcertionResponseMapper excertionResponseMapper;
    private final EntityManager entityManager;

    public ExcertionMoviePaginationDtoDaoImpl(ExcertionResponseMapper excertionResponseMapper, EntityManager entityManager) {
        this.excertionResponseMapper = excertionResponseMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<ExcertionResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return excertionResponseMapper.modelsToDTO(entityManager
                .createQuery("select e from Excertion e where e.movie=:movie")
                .setParameter("movie", parameters.get("movie"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList());
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (Long) entityManager.createQuery("select count (e) from Excertion e where e.movie=:movie")
                .setParameter("movie", parameters.get("movie"))
                .getSingleResult();
    }
}
