package com.example.qgame.services;

import com.example.qgame.Models.User;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(RegisterRequest request) {
        User user = request.toUser();

        return userRepository.save(user);
    }


    public void updatePasswordByEmail(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.updatePasswordByEmail(email, hashedPassword);
    }
}
