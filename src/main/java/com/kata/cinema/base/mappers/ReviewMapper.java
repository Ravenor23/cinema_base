package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entity.Review;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

  @Mapping( target = "fullName", expression = "java(review.getUser().getFirstName() + \" \" + review.getUser().getLastName())")
  ReviewResponseDto toDTO(Review review);

  List<ReviewResponseDto> modelsToDTO(List<Review> reviews);

  Review toEntity(ReviewResponseDto reviewResponseDto);

}
