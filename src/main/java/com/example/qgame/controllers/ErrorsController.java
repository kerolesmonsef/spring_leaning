package com.example.qgame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {

    @RequestMapping("/403")
    public String accessDenied() {
        return "errorPages/403";
    }
}
