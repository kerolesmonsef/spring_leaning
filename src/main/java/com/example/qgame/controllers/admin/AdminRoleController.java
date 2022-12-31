package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Permission;
import com.example.qgame.Models.Role;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.repositories.RoleRepository;
import com.example.qgame.requests.admin.RoleRequest;
import com.example.qgame.services.RoleServices;
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
public class AdminRoleController extends IController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AdminRepository adiAdminRepository;

    @Autowired
    private RoleServices roleService;

    @GetMapping("/roles")
    public ModelAndView index(Model model) {

        return new ModelAndView("/admin/authorities/roles")
                .addObject("roles", roleRepository.rolesWithPermissionsCount());
    }

    @GetMapping("/roles/create")
    public ModelAndView edit() {
        return this.edit(new Role());
    }

    @GetMapping("/roles/{role}/edit")
    public ModelAndView edit(Role role) {
        role.setName(role.getName().replace("ROLE_", ""));

        return new ModelAndView("/admin/authorities/add_edit_role")
                .addObject("role", role)
                .addObject("role_strings_permissions", role.getPermissions().stream().map(Permission::getName).toList())
                .addObject("permissions", permissionRepository.findAll());
    }


    @PutMapping("/roles/{role}/update")
    public ModelAndView update(@PathVariable("role") Role role, @Valid RoleRequest request, BindingResult result, RedirectAttributes attributes) {
        roleService.update(role, request);

        attributes.addFlashAttribute("alertSuccess", "Role Updated Successfully");

        return back();
    }

    @PostMapping("/roles/store")
    public String store(RoleRequest request, RedirectAttributes attributes) {
        roleService.create(request);

        attributes.addFlashAttribute("alertSuccess", "Role Created Successfully");

        return "redirect:/admin/authorities/roles";
    }

    @DeleteMapping("/roles/{role}/delete")
    public String delete(Role role, RedirectAttributes attributes) {

        boolean existsAdmin = adiAdminRepository.existsAdminForRole(role.getId());

        if (existsAdmin) {
            attributes.addFlashAttribute("alertError", "You Can't Delete This Role");
        } else {
            attributes.addFlashAttribute("alertSuccess", "Role Deleted Successfully");
            roleRepository.delete(role);
        }

        return "redirect:/admin/authorities/roles";
    }
}
