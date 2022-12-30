package com.example.qgame.repositories;

import com.example.qgame.Models.Admin;
import com.example.qgame.helpers.dto.AdminRoleCountPermissionCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByEmail(String email);

    @Query(value = "SELECT a.id , a.name, (SELECT COUNT(ar.admin_id) from admins_roles ar  WHERE ar.admin_id = a.id) rolesCount , (SELECT COUNT(ap.admin_id) FROM admins_permissions ap WHERE ap.admin_id = a.id) as permissionsCount FROM admins a",nativeQuery = true)
    List<AdminRoleCountPermissionCountDto> getAdminsWithRolesCountAndPermissionsCount();
}
