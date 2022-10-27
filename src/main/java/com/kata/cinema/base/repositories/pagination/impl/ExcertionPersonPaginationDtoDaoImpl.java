package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.mappers.ExcertionResponseMapper;
import com.kata.cinema.base.models.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.repositories.pagination.ExcertionPersonPaginationDtoDao;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ExcertionPersonPaginationDtoDaoImpl implements ExcertionPersonPaginationDtoDao {
    private final ExcertionResponseMapper excertionResponseMapper;
    private final EntityManager entityManager;

    public ExcertionPersonPaginationDtoDaoImpl(ExcertionResponseMapper excertionResponseMapper, EntityManager entityManager) {
        this.excertionResponseMapper = excertionResponseMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<ExcertionResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return excertionResponseMapper.modelsToDTO(entityManager
                .createQuery("select e from Excertion e where e.person=:person")
                .setParameter("person", parameters.get("person"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList());
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (Long) entityManager.createQuery("select count (e) from Excertion e where e.person=:person")
                .setParameter("person", parameters.get("person"))
                .getSingleResult();
    }
}
