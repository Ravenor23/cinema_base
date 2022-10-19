package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.UserMapper;
import com.kata.cinema.base.models.dto.request.UserRegistrationRequestDto;
import com.kata.cinema.base.models.entity.Role;
import com.kata.cinema.base.models.entity.User;
import com.kata.cinema.base.service.entity.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserRegistrationService {

    private final UserMapper userMapper;
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserMapper userMapper, RoleService roleService,
                                   UserService userService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        User userToRegister = userMapper.toEntity(userRegistrationRequestDto);
        Role defaultUserRole = roleService.findRoleByName("ROLE_USER");
        userToRegister.setRoles(Collections.singleton(defaultUserRole));
        String newUserPassword = passwordEncoder.encode(userRegistrationRequestDto.getPassword());
        userToRegister.setPassword(newUserPassword);
        userService.save(userToRegister);


    }


}
