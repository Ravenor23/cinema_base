package com.kata.cinema.base.repositories.pagination.impl;

import com.kata.cinema.base.mappers.ScoreMovieMapper;
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

    private final ScoreMovieMapper scoreMovieMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ScoreMoviePaginationDtoDaoImpl(ScoreMovieMapper scoreMovieMapper) {
        this.scoreMovieMapper = scoreMovieMapper;
    }

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage,
                                                   Integer itemsOnPage,
                                                   Map<String, Object> parameters) {
        SortScoreType sortEnum = (SortScoreType) parameters.get("sortScoreType");
        String stringSortType = "s.date";
        switch (sortEnum) {
            case DATE_ASC -> stringSortType = " order by s.date";
            case SCORE_ASC -> stringSortType = " order by s.score";
            case NAME_ASC -> stringSortType = " order by m.name";
            case COUNT_SCORE_ASC -> stringSortType = " group by s.id order by count(m.id)";
            case DATE_RELEASE_ASC -> stringSortType = " order by m.dataRelease";
        }

        List<ScoreMovieResponseDto> scoreMovieResponseDto = scoreMovieMapper.toScoreMovieResponseDto(
                entityManager.createQuery("select s from Score s left join"
                                + " Movie m on s.movie.id = m.id where s.user.id =: user"
                                + stringSortType)
                        .setParameter("user", parameters.get("user"))
                        .setFirstResult((currentPage - 1) * itemsOnPage)
                        .setMaxResults(itemsOnPage)
                        .getResultList());

        scoreMovieResponseDto = setAverageAndCount(scoreMovieResponseDto);

        return scoreMovieResponseDto;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (Long) entityManager.createQuery("SELECT COUNT (s) FROM Score s").getSingleResult();
    }

    public List<ScoreMovieResponseDto> setAverageAndCount(List<ScoreMovieResponseDto> scoreMovieResponseDto) {
        for (ScoreMovieResponseDto dto : scoreMovieResponseDto) {
            Long count = (Long) entityManager
                    .createQuery("select count(s) from Score s where s.movie.id = "
                            + dto.getMovieId()).getSingleResult();
            Double average = (Double) entityManager
                    .createQuery("select avg(score) as score_avg from Score where movie.id = "
                            + dto.getMovieId()).getSingleResult();
            dto.setCountScore(count);
            dto.setAvgScore(Math.round((average) * 100.0) / 100.0);
        }
        return scoreMovieResponseDto;
    }

    // возможно, закомментированный метод будет быстрее работать, там вроде меньше запросов к базе делается

//    public List<ScoreMovieResponseDto> setAverageAndCount(List<ScoreMovieResponseDto> scoreMovieResponseDto) {
//        Set<Long> movieIds = scoreMovieResponseDto.stream()
//                .map(ScoreMovieResponseDto::getMovieId).collect(Collectors.toSet());
//        List<Score> scoreList = new ArrayList<>();
//        for (Long movieId:movieIds) {
//            scoreList.addAll(getScoreByMovieId(movieId));
//        }
//        Map<Long, Double> averageScoreMap = new HashMap<>();
//        Map<Long, Long> countScoreMap = new HashMap<>();
//        for (Score score:scoreList) {
//            Long movieId = score.getMovie().getId();
//            averageScoreMap.merge(movieId, Double.valueOf(score.getScore()), Double::sum);
//            countScoreMap.merge(movieId, 1L, Long::sum);
//        }
//        for (ScoreMovieResponseDto dto: scoreMovieResponseDto) {
//            Long movieId = dto.getMovieId();
//            Long cntScore = countScoreMap.get(movieId);
//            Double avgScore = Math.round((averageScoreMap.get(movieId) / cntScore) * 100.0) / 100.0;
//            dto.setAvgScore(avgScore);
//            dto.setCountScore(cntScore);
//        }
//        return scoreMovieResponseDto;
//    }
//
//    public List<Score> getScoreByMovieId(Long movieId) {
//        return entityManager.createQuery(
//                        "select s from Score s where s.movie.id =: movieId")
//                .setParameter("movieId", movieId)
//                .getResultList();
//    }

}
