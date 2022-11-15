package com.kata.cinema.base.models.dto.response;

import com.kata.cinema.base.models.enums.TypeReview;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
  private Long id;
  private TypeReview typeReview;
  private String title;
  private String description;
  private String fullName;
  private LocalDate date;

}
