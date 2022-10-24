package com.kata.cinema.base.models.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchPersonDto {
    private Long id;
    private String photoUrl;
    private String fullName;
    private String originalFullName;
    private LocalDate birthday;

}
