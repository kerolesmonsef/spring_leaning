package com.example.qgame.controllers.user;

import com.example.qgame.Models.User;
import com.example.qgame.configs.auth.JwtUtil;
import com.example.qgame.helpers.Response;
import com.example.qgame.requests.LoginRequest;
import com.example.qgame.resources.UserResource;
import com.example.qgame.services.AuthinticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {


    @Autowired
    private AuthinticationService authinticationService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @PostMapping("jwt/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest request) {

        User user = authinticationService.login(request);
        if (user != null) {
            return new Response()
                    .add("token", jwtTokenUtil.generateToken(user))
                    .add("user", new UserResource(user))
                    .toResponseEntity();
        }
        return new Response()
                .setFail()
                .add("message", "unauthorized")
                .setHttpStatus(HttpStatus.UNAUTHORIZED)
                .toResponseEntity();

    }

}
