package com.kata.cinema.base.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    Long id;
    String email;
    String fullName;
    String birthday;
    String avatarUrl;

}
