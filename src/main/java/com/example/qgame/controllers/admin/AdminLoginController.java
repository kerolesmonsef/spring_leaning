package com.example.qgame.controllers.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @GetMapping("/admin/login")
    public String openLoginForm() {
        return "admin/login";
    }

    @RequestMapping(value = "/admin/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:/admin/login";
    }
}
