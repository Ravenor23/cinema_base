package com.kata.cinema.base.webapp.controllers;

import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.service.dto.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class SearchRestController {


    private final UserService userService;

    @Autowired
    public SearchRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/search?{email}")
    public ResponseEntity<List<UserResponseDto>> searchUserResponseDto(@PathVariable("email") String email) {
        List<UserResponseDto> userResponseDtoList = userService.searchByEmail(email);
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }
}
