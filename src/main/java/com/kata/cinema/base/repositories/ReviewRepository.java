package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query("select r from Review r LEFT JOIN FETCH r.movie order by r.date")
  List<Review> findListReviewsByMovieId(Long id);

}
