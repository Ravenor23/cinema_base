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
    private String fullName; //- конкатенация имени и фамилии
    private String originalFullName; //- конкатенация оригинальной имени и фамилии
    private LocalDate birthday;

}
