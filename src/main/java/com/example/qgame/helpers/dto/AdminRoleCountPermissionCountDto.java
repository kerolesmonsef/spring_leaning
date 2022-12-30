package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;


public interface AdminRoleCountPermissionCountDto {
    Long getId();

    String getName();

    Long getRolesCount();

    Long getPermissionsCount();

}
