package com.example.qgame.repositories;

import com.example.qgame.Models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("FROM Permission p WHERE p.name = :name")
    Permission findByName(@Param("name") String permissionName);
}
