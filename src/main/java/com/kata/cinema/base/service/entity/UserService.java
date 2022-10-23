package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> searchByEmail(String email);

    void save(User user);

    User getById(Long id);
}
