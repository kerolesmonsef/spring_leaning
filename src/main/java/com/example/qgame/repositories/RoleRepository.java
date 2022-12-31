package com.example.qgame.repositories;

import com.example.qgame.Models.Role;
import com.example.qgame.helpers.dto.RolePermissionCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("FROM Role r WHERE r.name = :name")
    Role findByName(@Param("name") String name);

    @Query(value = "SELECT R.id ,R.name , (SELECT COUNT(*) FROM roles_permissions P WHERE P.role_id = R.id ) permissionsCount from roles R",nativeQuery = true)
    List<RolePermissionCount> rolesWithPermissionsCount();
}
