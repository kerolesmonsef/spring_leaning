package com.example.qgame.requests;

import com.example.qgame.validations.Exists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordRequest {
    @NotNull
    @NotEmpty
    @Exists(entity = "User", column = "email")
    private String email;

    @NotNull
    @NotEmpty
    private String token;

    @NotNull
    @NotEmpty
    private String newPassword;
}
