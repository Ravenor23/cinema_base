package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.models.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.repositories.pagination.ScoreMoviePaginationDtoDao;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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

        if (stringSortType.equals("native query")) {
            try (Session session = entityManager.unwrap(Session.class)) {
                Query query = session.createNativeQuery("select s.id as id, s.score as score, s.date as date, m.time as" +
                                " time, m.id as movie, m.name as name, m.original_name as origin, m.data_release as release, " +
                                "(select avg(av.score) from Score av where av.movie_id = m.id) as average, " +
                                "(select count(cnt.id) from Score cnt where cnt.movie_id = m.id) as scount " +
                                "from Score s left join movies m on s.movie_id = m.id " +
                                "where s.user_id =:user group by s.id, m.id " +
                                "order by (select count(s1.score) from Score s1" +
                                " where s1.movie_id = s.movie_id group by movie_id)")
                        .setParameter("user", parameters.get("user"))
                        .setFirstResult((currentPage - 1) * itemsOnPage)
                        .setMaxResults(itemsOnPage).setResultTransformer(new ResultTransformer() {
                            @Override
                            public Object transformTuple(Object[] tuples, String[] aliases) {
                                ScoreMovieResponseDto dto = new ScoreMovieResponseDto();
                                dto.setId(((BigInteger) tuples[0]).longValue());
                                dto.setScore((Integer) tuples[1]);
                                dto.setDate(((Date) tuples[2]).toLocalDate());
                                dto.setTime((Integer) tuples[3]);
                                dto.setMovieId(((BigInteger) tuples[4]).longValue());
                                dto.setName((String) tuples[5]);
                                dto.setOriginalName((String) tuples[6]);
                                dto.setDateRelease(((Date) tuples[7]).toLocalDate());
                                dto.setAvgScore(((BigDecimal) tuples[8]).doubleValue());
                                dto.setCountScore(((BigInteger) tuples[9]).longValue());
                                return dto;
                            }

                            @Override
                            public List transformList(List list) {
                                return list;
                            }
                        });
                return query.getResultList();
            }
        }
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
            case COUNT_SCORE_ASC -> stringSortType = "native query";
            case DATE_RELEASE_ASC -> stringSortType = " order by m.dataRelease";

            default -> throw new IllegalStateException("Unexpected value of SortScoreType enum: " + sortEnum);
        }
        return stringSortType;
    }
}
