package com.example.qgame.services;


import com.example.qgame.Models.Permission;
import com.example.qgame.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission firstOrCreate(String permissionName) {
        Permission permission = permissionRepository.findByName(permissionName);

        if (permission != null) {
            return permission;
        }

        return permissionRepository.save(new Permission(permissionName));
    }
}
