package com.kata.cinema.base.models.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonViewResponseDto {
  private Long id;
  private Double height;
  private LocalDate birthday;
  private String placeBirthday;
  private String photoUrl;
  private String fullName;
  private String originalFullName;
  private List<GenreResponseDto> genres;
  private List<ProfessionResponseDto> profession;
}
