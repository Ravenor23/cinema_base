package com.kata.cinema.base.models.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PersonRequestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Double height;
    private LocalDate birthday;
    private String placeBirthday;
    private String originalName;
    private String originalLastName;
}
