package com.example.qgame.services;

import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.requests.admin.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Role firstOrCreate(String roleName) {
        Role role = roleRepository.findByName(roleName);

        if (role != null) {
            return role;
        }

        return roleRepository.save(new Role(roleName));
    }

    public void update(Role role, RoleRequest request) {
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        role
                .setName("ROLE_" + request.getName())
                .setPermissions(permissions);

        roleRepository.save(role);
    }

    public Role create(RoleRequest request) {
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        Role role = new Role()
                .setName("ROLE_" + request.getName())
                .setPermissions(permissions);

        return roleRepository.save(role);
    }
}
