package com.example.qgame.services;

import com.example.qgame.Models.PasswordReset;
import com.example.qgame.helpers.ResetPasswordStatus;
import com.example.qgame.repositories.PasswordResetRepository;
import com.example.qgame.requests.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final PasswordResetRepository passwordResetRepository;
    private final UserService userService;

    public PasswordReset create(String email) {
        Optional<PasswordReset> passwordResetOptional = passwordResetRepository.findByEmail(email);
        passwordResetOptional.ifPresent(passwordResetRepository::delete);
        Date expiredAt = new Date(Calendar.getInstance().getTimeInMillis() + (PasswordReset.EXPIRATION_HOUR * 1000));
        PasswordReset passwordReset = new PasswordReset(email, UUID.randomUUID().toString(), expiredAt);
        return passwordResetRepository.save(passwordReset);
    }

    public ResetPasswordStatus resetPassword(ResetPasswordRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().stream().findFirst().orElseThrow();
            return new ResetPasswordStatus(false, error.getField() + " - " + error.getDefaultMessage());
        }

        Optional<PasswordReset> passwordResetOptional = passwordResetRepository.findByEmail(request.getEmail());

        if (passwordResetOptional.isEmpty()) {
            return new ResetPasswordStatus(false, "invalid email");
        }

        PasswordReset passwordReset = passwordResetOptional.get();

        if (!Objects.equals(passwordReset.getToken(), request.getToken())) {
            return new ResetPasswordStatus(false, "invalid token");
        }


        passwordResetRepository.deleteByEmail(request.getEmail());
        userService.updatePasswordByEmail(request.getEmail(), request.getNewPassword());

        return new ResetPasswordStatus(true, "password updated successfully");
    }
}
