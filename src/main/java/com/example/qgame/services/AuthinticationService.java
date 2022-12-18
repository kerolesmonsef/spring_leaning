package com.example.qgame.services;

import com.example.qgame.Models.User;
import com.example.qgame.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthinticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public User login(LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            return (User) authenticate.getPrincipal();

        } catch (BadCredentialsException ex) {
            return null;
        }
    }
}
