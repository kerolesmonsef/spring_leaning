package com.example.qgame.services;

import com.example.qgame.Models.Admin;
import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.requests.admin.AdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final CacheService cacheService;

    public void update(Admin admin, AdminRequest request) {
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        admin.setRoles(roles)
                .setPermissions(permissions)
                .setName(request.getName());

        cacheService.removeByKey("admin_Authority");

        adminRepository.save(admin);
    }

    public Admin create(AdminRequest request) {
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        Admin admin = new Admin()
                .setRoles(roles)
                .setPermissions(permissions)
                .setName(request.getName());

        cacheService.removeByKey("admin_Authority");

        return adminRepository.save(admin);
    }

    @Transactional
    @Cacheable("admin_Authority")
    public List<GrantedAuthority> authorities(Admin admin) {
        List<Role> roles = admin.getRoles();
        List<String> authorities = new ArrayList<>(roles.stream().map(Role::getName).toList());
        for (Role role : roles) {
            role.getPermissions().forEach(permission -> authorities.add(permission.getName()));
        }
        return authorities.stream().map(a -> (GrantedAuthority) () -> a).toList();
    }

}
