package com.example.qgame.services;

import com.example.qgame.Models.Role;
import com.example.qgame.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServices {
    @Autowired
    private RoleRepository roleRepository;

    public Role firstOrCreate(String roleName) {
        Role role = roleRepository.findByName(roleName);

        if (role != null) {
            return role;
        }

        return roleRepository.save(new Role(roleName));
    }
}
