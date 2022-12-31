package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Permission;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.PermissionRepository;
import com.example.qgame.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/authorities")
public class AdminPermissionController extends IController {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    public ModelAndView index(Model model) {

        return new ModelAndView("/admin/authorities/permissions")
                .addObject("activePermission",true)
                .addObject("permissions", permissionRepository.findAll(Sort.by(Sort.Direction.ASC,"id")));
    }


    @DeleteMapping("/permissions/{permission}/delete")
    public String delete(Permission permission, RedirectAttributes attributes) {

        boolean existsPermission = permissionService.isUsed(permission);

        if (existsPermission) {
            attributes.addFlashAttribute("alertError", "You Can't Delete This Permission");
        } else {
            attributes.addFlashAttribute("alertSuccess", "Permission Deleted Successfully");
            permissionRepository.delete(permission);
        }

        return "redirect:/admin/authorities/permissions";
    }
}
