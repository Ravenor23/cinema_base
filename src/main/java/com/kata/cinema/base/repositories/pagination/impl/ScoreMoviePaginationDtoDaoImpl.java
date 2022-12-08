package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.repositories.pagination.ScoreMoviePaginationDtoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ScoreMoviePaginationDtoDaoImpl implements ScoreMoviePaginationDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage,
                                                   Integer itemsOnPage,
                                                   Map<String, Object> parameters) {
        SortScoreType sortEnum = (SortScoreType) parameters.get("sortScoreType");
        String stringSortType = getSortQueryPart(sortEnum);

        List<ScoreMovieResponseDto> scoreMovieResponseDto =
                entityManager.createQuery("select new com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto" +
                                "(s.id, s.score, s.date, m.time, m.id, m.name, m.originName, m.dataRelease, " +
                                "(select avg(av.score) from Score av where av.movie.id = m.id), " +
                                "(select count(cnt.id) from Score cnt where cnt.movie.id = m.id)) " +
                                "from Score s left join Movie m on s.movie.id = m.id " +
                                "where s.user.id =: user group by s.id, m.id"
                                + stringSortType)
                        .setParameter("user", parameters.get("user"))
                        .setFirstResult((currentPage - 1) * itemsOnPage)
                        .setMaxResults(itemsOnPage)
                        .getResultList();

        return scoreMovieResponseDto;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (Long) entityManager.createQuery("SELECT COUNT (s) FROM Score s").getSingleResult();
    }

    public String getSortQueryPart(SortScoreType sortEnum) {
        String stringSortType;
        switch (sortEnum) {
            case DATE_ASC -> stringSortType = " order by s.date";
            case SCORE_ASC -> stringSortType = " order by s.score";
            case NAME_ASC -> stringSortType = " order by m.name";
            case COUNT_SCORE_ASC -> stringSortType = " order by (select count(s1.score) from Score s1" +
                    " where s1.movie.id = s.movie.id group by s1.movie.id)";
            case DATE_RELEASE_ASC -> stringSortType = " order by m.dataRelease";

            default -> throw new IllegalStateException("Unexpected value: " + sortEnum);
        }
        return stringSortType;
    }
}
