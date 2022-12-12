package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.User;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

public class UserSeeder extends ISeeder<User> {

    private final UserRepository userRepository;

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected Collection<User> data() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new ArrayList<>() {{
            add(new User()
                    .setName("user1")
                    .setEmail("user1@gmail.com")
                    .setPassword(passwordEncoder.encode("1234"))
            );

            add(new User()
                    .setName("user2")
                    .setEmail("user2@gmail.com")
                    .setPassword(passwordEncoder.encode("1234"))
            );
        }};
    }

    @Override
    public void seed() {
        userRepository.saveAll(data());
    }
}
