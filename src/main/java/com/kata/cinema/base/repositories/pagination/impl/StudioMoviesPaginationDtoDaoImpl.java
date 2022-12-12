package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.models.dto.response.ScoreMovieV2ResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.repositories.pagination.StudioMoviesPaginationDtoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class StudioMoviesPaginationDtoDaoImpl implements StudioMoviesPaginationDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ScoreMovieV2ResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        List<ScoreMovieV2ResponseDto> scoreMovieV2ResponseDtos = entityManager.createQuery(
                "select distinct new com.kata.cinema.base.models.dto.response.ScoreMovieV2ResponseDto("
                        + "m.id, m.time, m.name, m.originName, m.dataRelease, "
                        + "(select avg(s.score) from Score s where m.id=s.movie.id) as average_score, (select count(s.score) from Score s where m.id=s.movie.id) as count_score) "
                + "from Movie m "
                        + "LEFT JOIN Score s on s.movie.id = m.id "
                        + "LEFT JOIN ProductionStudioMovie prod on prod.movie.id = m.id "
                + "where prod.studio.id = :studioId "
                + sort(parameters))
                .setParameter("studioId", parameters.get("studioId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
        return scoreMovieV2ResponseDtos;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        Long total = (Long) entityManager.createQuery("select count(m.id) from Movie m "
        + "INNER JOIN ProductionStudioMovie prod on prod.movie.id = m.id "
        + "where prod.studio.id = :studioId")
                .setParameter("studioId", parameters.get("studioId"))
                .getSingleResult();
        return total;
    }

    public String sort(Map<String, Object> parameters) {
       if(parameters.get("sortScoreType") != null) {
            return switch ((SortScoreType) parameters.get("sortScoreType")) {
                case DATE_ASC -> "order by m.time asc";
                case NAME_ASC -> "order by m.name asc";
                case COUNT_SCORE_ASC -> "order by count_score asc";
                case SCORE_ASC -> "order by average_score asc";
                case DATE_RELEASE_ASC -> "order by m.dateRelease asc";
            };
        }
       return "";
    }


}
