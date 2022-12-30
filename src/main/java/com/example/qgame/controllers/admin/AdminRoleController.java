package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Admin;
import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/authorities")
public class AdminRoleController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/admins")
    public ModelAndView listAdmins(Model model) {

        return new ModelAndView("/admin/authorities/auth_admins")
                .addObject("admins", adminRepository.getAdminsWithRolesCountAndPermissionsCount());
    }

    @GetMapping("/admins/{admin}/edit")
    public ModelAndView adminRoles(@PathVariable("admin") Admin admin) {

        return new ModelAndView("/admin/authorities/edit_auth_admin")
                .addObject("admin", admin)
                .addObject("admin_strings_roles", admin.getRoles().stream().map(Role::getName).toList())
                .addObject("admin_strings_permissions", admin.getPermissions().stream().map(Permission::getName).toList())
                .addObject("roles", roleRepository.findAll())
                .addObject("permissions", permissionRepository.findAll())
                .addObject("admins", adminRepository.getAdminsWithRolesCountAndPermissionsCount());

    }


}
