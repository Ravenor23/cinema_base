package com.kata.cinema.base.webapp.controllers.security;


import com.kata.cinema.base.models.dto.request.AuthRequestDto;
import com.kata.cinema.base.models.dto.request.UserRegistrationRequestDto;
import com.kata.cinema.base.security.JWTUtil;
import com.kata.cinema.base.service.dto.impl.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserRegistrationService userRegistrationService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                          UserRegistrationService userRegistrationService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDto authRequestDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(), authRequestDto.getPassword());

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(Map.of("error", "wrong password"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Map.of("token", jwtUtil.generateToken(authRequestDto.getUserName())),
                HttpStatus.OK);

    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {

        if (!userRegistrationRequestDto.getPassword().equals(userRegistrationRequestDto.getConfirmPassword())) {
            return new ResponseEntity<>(Map.of("error", "wrong password confirmation"), HttpStatus.BAD_REQUEST);
        }

        userRegistrationService.registerNewUser(userRegistrationRequestDto);

        return new ResponseEntity<>(Map.of("token", jwtUtil.generateToken(userRegistrationRequestDto.getLogin())),
                HttpStatus.OK);

    }

}
