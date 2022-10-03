package com.example.qgame.services;

import com.example.qgame.Models.User;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(RegisterRequest request) {
        User user = request.toUser();

        return userRepository.save(user);
    }

}
