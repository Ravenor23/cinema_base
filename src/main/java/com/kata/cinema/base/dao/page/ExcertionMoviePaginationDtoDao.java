package com.kata.cinema.base.dao.page;

import com.kata.cinema.base.mappers.ExcertionResponseMapper;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.repositories.PaginationDtoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Repository
public class ExcertionMoviePaginationDtoDao implements PaginationDtoDao<ExcertionResponseDto> {

    private final ExcertionResponseMapper excertionResponseMapper;
    private final EntityManager entityManager;

    public ExcertionMoviePaginationDtoDao(ExcertionResponseMapper excertionResponseMapper, EntityManager entityManager) {
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
