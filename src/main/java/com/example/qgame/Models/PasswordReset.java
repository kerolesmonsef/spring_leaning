package com.example.qgame.Models;

import com.example.qgame.helpers.Helper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_resets")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class PasswordReset {
    public static final int EXPIRATION_HOUR = 60 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String token;

    @NonNull
    @Column(name = "expired_at")
    private Date expiredAt;


}
