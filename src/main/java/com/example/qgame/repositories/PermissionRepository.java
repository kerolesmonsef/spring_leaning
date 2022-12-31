package com.example.qgame.repositories;

import com.example.qgame.Models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("FROM Permission p WHERE p.name = :name")
    Permission findByName(@Param("name") String permissionName);

    @Query("FROM Permission p WHERE p.name in :names")
    List<Permission> findByNames(@Param("names") List<String> names);

    @Query("SELECT COUNT(P) > 0 FROM Permission P INNER JOIN P.roles WHERE P.id = :#{#permission.id}")
    Boolean isUsedByAnyRole(@Param("permission") Permission permission);

    @Query("SELECT COUNT(P) > 0 FROM Permission P INNER JOIN P.admins WHERE P.id = :#{#permission.id}")
    Boolean isUsedByAnyAdmin(@Param("permission") Permission permission);
}
