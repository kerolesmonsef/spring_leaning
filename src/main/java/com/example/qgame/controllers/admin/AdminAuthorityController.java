package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Admin;
import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.requests.admin.AdminRequest;
import com.example.qgame.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/authorities")
public class AdminAuthorityController extends IController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public ModelAndView listAdmins(Model model) {

        return new ModelAndView("/admin/authorities/auth_admins")
                .addObject("admins", adminRepository.getAdminsWithRolesCountAndPermissionsCount());
    }


    @GetMapping("/admins/create")
    public ModelAndView createAdmin() {
        return this.edit(new Admin());
    }


    @GetMapping("/admins/{admin}/edit")
    public ModelAndView edit(@PathVariable("admin") Admin admin) {

        return new ModelAndView("/admin/authorities/edit_auth_admin")
                .addObject("admin", admin)
                .addObject("admin_strings_roles", admin.getRoles().stream().map(Role::getName).toList())
                .addObject("admin_strings_permissions", admin.getPermissions().stream().map(Permission::getName).toList())
                .addObject("roles", roleRepository.findAll())
                .addObject("permissions", permissionRepository.findAll())
                .addObject("admins", adminRepository.getAdminsWithRolesCountAndPermissionsCount());

    }

    @PutMapping("/admins/{admin}/update")
    public ModelAndView update(@PathVariable("admin") Admin admin, @Valid AdminRequest request, BindingResult result, RedirectAttributes attributes) {

        adminService.update(admin, request);

        attributes.addFlashAttribute("alertSuccess", "Admin Updated Successfully");

        return back();
    }


    @PostMapping("/admins/store")
    public String storeAdmin(AdminRequest request, RedirectAttributes attributes) {
        adminService.create(request);

        attributes.addFlashAttribute("alertSuccess", "Admin Created Successfully");

        return "redirect:/admin/authorities/admins";
    }
}
