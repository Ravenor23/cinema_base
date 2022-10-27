package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.repositories.pagination.ReviewPaginationDtoDao;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewPaginationDtoDaoImpl implements ReviewPaginationDtoDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<ReviewResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage,
      Map<String, Object> parameters) {
    List<ReviewResponseDto> reviewResponseDtos = entityManager.createQuery(
           "select new com.kata.cinema.base.models.dto.response.ReviewResponseDto("
               + "r.id, r.typeReview, r.title, r.description, concat(r.user.firstName, ' ', r.user.lastName) as fullName, r.date) "
                + "from Review r "
                + "where r.movie.id = :movieId "
                + "and (r.typeReview = :typeReview or :typeReview is null) "
                + sort(parameters))
        .setParameter("movieId", parameters.get("movieId"))
        .setParameter("typeReview", parameters.getOrDefault("typeReview", null))
        .setFirstResult((currentPage - 1) * itemsOnPage)
        .setMaxResults(itemsOnPage)
        .getResultList();
    return reviewResponseDtos;
  }

  @Override
  public Long getResultTotal(Map<String, Object> parameters) {
    Long total = (Long) entityManager.createQuery("select count (r) from Review r "
            + "where r.movie.id = :movieId "
            + "and (r.typeReview = :typeReview or :typeReview is null) ")
        .setParameter("movieId", parameters.get("movieId"))
        .setParameter("typeReview", parameters.getOrDefault("typeReview", null))
        .getSingleResult();
    return total;
  }

  String sort(Map<String, Object> parameters) {
    if (parameters.get("reviewSortType") != null) {
      switch ((ReviewSortType) parameters.get("reviewSortType")) {
        case DATE_ASC:
          return "order by r.date asc";
        case DATE_DESC:
          return "order by r.date desc";
        default:
          return "";
      }
    }
    return "";
  }
}
