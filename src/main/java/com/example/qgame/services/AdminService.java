package com.example.qgame.services;

import com.example.qgame.Models.Admin;
import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.requests.admin.AdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public void update(Admin admin, AdminRequest request) {
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        admin.setRoles(roles)
                .setPermissions(permissions)
                .setName(request.getName());

        adminRepository.save(admin);
    }

    public Admin create(AdminRequest request) {
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        Admin admin = new Admin()
                .setRoles(roles)
                .setPermissions(permissions)
                .setName(request.getName());

        System.out.println("5555555555");
        System.out.println(admin);

        return adminRepository.save(admin);
    }
}
