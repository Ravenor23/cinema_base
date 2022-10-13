package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.entity.Review;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

  ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

  ReviewResponseDto toDTO(Review review);

  List<ReviewResponseDto> modelsToDTO(List<Review> reviews);

  Review toEntity(ReviewResponseDto reviewResponseDto);

}
