package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.mappers.UserMapper;
import com.kata.cinema.base.models.dto.response.UserResponseDto;
import com.kata.cinema.base.models.entity.User;
import com.kata.cinema.base.repositories.UserRepository;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> searchByEmail(String email) {
        List<User> userList = userRepository.findAllByEmailContainingIgnoreCase(email);
        return userMapper.toDTOList(userList);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
