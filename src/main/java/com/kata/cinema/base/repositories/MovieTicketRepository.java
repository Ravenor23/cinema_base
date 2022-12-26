package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.dto.response.MovieResponseDto;
import com.kata.cinema.base.models.entity.MovieTicket;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTicketRepository extends JpaRepository<MovieTicket, Long> {

    @Modifying
    @Query("delete from MovieTicket where endShowDate < :currentDate")
    void deleteByEndShowDate(LocalDate currentDate);

    @Query(
        "select new com.kata.cinema.base.models.dto.response.MovieResponseDto(m.id, m.name,m.originName, m.time, m.dataRelease, m.countries,(select count(s) from Score s where s.movie.id=m.id))"
            + " from Movie m join MovieTicket mt on mt.movie.id=m.id join m.genres g on g.id=:genreId order by mt.endShowDate")
    List<MovieResponseDto> findAllAndOrderByEndShowDate(Long genreId);
}
