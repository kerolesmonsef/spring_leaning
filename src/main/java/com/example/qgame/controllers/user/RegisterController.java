package com.example.qgame.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public String register(){
        // save user into db
        return "shit";
    }
}
