package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionSeeder extends ISeeder<String> {

    private final PermissionService permissionService;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    protected Collection<String> data() {
        return Arrays.asList(
                "ROLES_PERMISSIONS",
                "ADD_USER", "EDIT_USER", "SHOW_USER", "LIST_USER",
                "ADD_PRODUCT", "EDIT_PRODUCT", "SHOW_PRODUCT", "LIST_PRODUCT",
                "ADD_BLOG", "EDIT_BLOG", "SHOW_BLOG", "LIST_BLOG",
                "SEND_EMAIL", "CONTACT_US"
        );
    }

    private void seedPermissions() {
        for (String permissionName : data()) {
            permissionService.firstOrCreate(permissionName);
        }
    }

    @Transactional
    protected void assignRoles() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        adminRole.setPermissions(permissionRepository.findAll());
        roleRepository.save(adminRole);

        Role supervisorRole = roleRepository.findByName("ROLE_SUPERVISOR");
        List<Permission> supervisorPermissions = permissionRepository.findByNames(Arrays.asList("ADD_PRODUCT", "EDIT_PRODUCT", "SHOW_PRODUCT", "LIST_PRODUCT", "ADD_BLOG", "EDIT_BLOG", "SHOW_BLOG", "LIST_BLOG", "SEND_EMAIL", "CONTACT_US"));
        supervisorRole.setPermissions(supervisorPermissions);
        roleRepository.save(supervisorRole);

        Role supportRole = roleRepository.findByName("ROLE_SUPPORT");
        List<Permission> supportPermissions = permissionRepository.findByNames(Arrays.asList("ADD_USER", "SHOW_PRODUCT", "SHOW_BLOG", "LIST_BLOG", "CONTACT_US", "SEND_EMAIL"));
        supportRole.setPermissions(supportPermissions);
        roleRepository.save(supportRole);
    }

    @Override
    public void seed() {
        seedPermissions();

        assignRoles();
    }
}
