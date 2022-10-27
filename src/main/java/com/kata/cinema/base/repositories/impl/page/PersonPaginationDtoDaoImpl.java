package com.kata.cinema.base.repositories.impl.page;

import com.kata.cinema.base.models.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.repositories.PersonPaginationDtoDao;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PersonPaginationDtoDaoImpl implements PersonPaginationDtoDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<PersonViewResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage,
      Map<String, Object> parameters) {
    List<PersonViewResponseDto> personViewResponseDtos = entityManager.createQuery(
        "select new com.kata.cinema.base.models.dto.response.PersonViewResponseDto(p.id, p.height, p.dateBirth, p.placeBirth, p.photoUrl, concat(p.firstName, ' ', p.lastName), "
            + "concat(p.originalName, ' ', p.originalLastName))"
            + "from Person p "
            + "where p.id = :personId "
            + "").getResultList();
    return null;
  }

  @Override
  public Long getResultTotal(Map<String, Object> parameters) {
    return null;
  }
}
