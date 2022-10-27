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

  Long id;
  Double height;
  LocalDate birthday;
  String placeBirthday;
  String photoUrl;
  String fullName;
  String originalFullName;
  List<GenreResponseDto> genres;
  List<ProfessionResponseDto> profession;

  public PersonViewResponseDto(Long id, Double height, LocalDate birthday, String placeBirthday,
      String photoUrl, String fullName, String originalFullName, List<GenreResponseDto> genres,
      List<ProfessionResponseDto> profession) {
    this.id = id;
    this.height = height;
    this.birthday = birthday;
    this.placeBirthday = placeBirthday;
    this.photoUrl = photoUrl;
    this.fullName = fullName;
    this.originalFullName = originalFullName;
    this.genres = genres;
    this.profession = profession;
  }
}
