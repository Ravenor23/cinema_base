package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.ReviewMapper;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.repositories.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;

  @Autowired
  public ReviewService (ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
    this.reviewRepository = reviewRepository;
    this.reviewMapper = reviewMapper;
  }

  public List<ReviewResponseDto> getReviews(Long id) {
    return reviewMapper.modelsToDTO(reviewRepository.findListReviewsByMovieId(id));
  }
}
