package com.example.qgame.requests;

import com.example.qgame.Models.User;
import com.example.qgame.validations.Exists;
import com.example.qgame.validations.Unique;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Unique(entity = "User",column = "email")
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public User toUser() {
        return new User()
                .setName(name)
                .setEmail(email)
                .setPassword(new BCryptPasswordEncoder().encode(password));
    }
}
