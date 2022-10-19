package com.kata.cinema.base.service.dto.impl;


import com.kata.cinema.base.models.entity.Role;
import com.kata.cinema.base.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
