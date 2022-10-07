package com.example.qgame.controllers.user;

import com.example.qgame.Models.User;
import com.example.qgame.configs.auth.JwtUtil;
import com.example.qgame.helpers.Response;
import com.example.qgame.requests.RegisterRequest;
import com.example.qgame.resources.UserResource;
import com.example.qgame.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) {

        User user = userService.create(request);

        return new Response()
                .add("token", jwtUtil.generateToken(user))
                .add("user", new UserResource(user))
                .responseEntity();
    }
}
