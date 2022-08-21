package com.example.qgame.controllers.admin;

import com.example.qgame.helpers.annotations.AdminController;
import org.springframework.web.bind.annotation.GetMapping;

@AdminController
public class AdminHomeController {

    @GetMapping("")
    public String home() {
        return "admin/index";
    }
}
