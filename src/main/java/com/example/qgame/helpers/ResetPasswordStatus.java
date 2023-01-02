package com.example.qgame.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResetPasswordStatus {
    private final boolean isSuccess;
    private final String message;

    public boolean isFail() {
        return !isSuccess;
    }
}
