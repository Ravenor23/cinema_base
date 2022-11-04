package com.kata.cinema.base.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MovieRequestDto {

  @NotBlank
  private String name;

  private String countries;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dateRelease;

  @NotBlank
  private RARS rars;

  @NotBlank
  private MPAA mpaa;

  @NotBlank
  private String time;

  private String description;

  @NotBlank
  private String originalName;

  private Set<Genre> genreIds;

}
