package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> searchByEmail(String email);

    void save(User user);
}
