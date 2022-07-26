package com.example.qgame.configs;

import com.example.qgame.Models.Admin;
import com.example.qgame.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AdminRepositoryUserDetailsService implements UserDetailsService {


    @Autowired
    private AdminRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = repository.findByEmail(email).orElse(null);
        System.out.println(admin);
        if (admin == null) {
            throw new UsernameNotFoundException("user not found :(");
        }

        return admin;
    }
}
