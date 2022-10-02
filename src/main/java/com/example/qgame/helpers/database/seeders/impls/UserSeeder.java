package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

public class UserSeeder extends ISeeder<User> {
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
        QGameApplication.getBean(UserRepository.class).saveAll(data());
    }
}
