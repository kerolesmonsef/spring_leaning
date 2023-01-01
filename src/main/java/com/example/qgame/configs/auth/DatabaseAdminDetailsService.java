package com.example.qgame.configs.auth;

import com.example.qgame.Models.Admin;
import com.example.qgame.repositories.AdminRepository;
import com.example.qgame.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DatabaseAdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Admin admin = adminRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("admins not found :("));


        return new User(
                admin.getEmail(),
                admin.getPassword(),
                true,
                true,
                true,
                true,
                adminService.authorities(admin)
        );
    }


}


