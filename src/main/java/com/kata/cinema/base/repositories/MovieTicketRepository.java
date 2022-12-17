package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.MovieTicket;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTicketRepository extends JpaRepository<MovieTicket, Long> {

    @Modifying
    @Query("delete from MovieTicket where endShowDate < :currentDate")
    void deleteByEndShowDate(LocalDate currentDate);
}
