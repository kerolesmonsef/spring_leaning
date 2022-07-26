package com.example.qgame.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestAuthController {
    @GetMapping("/")
    public ModelAndView defaultHome() {
        return new ModelAndView("login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/dashboard")
    public ModelAndView userDashboard() {
        return new ModelAndView("dashboard");
    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin/login");
    }

    @GetMapping("/admin/login")
    public ModelAndView adminLogin() {
        return new ModelAndView("admin/login");
    }

    @GetMapping("/admin/dashboard")
    public ModelAndView adminDashboard() {
        return new ModelAndView("admin/dashboard");
    }
}
