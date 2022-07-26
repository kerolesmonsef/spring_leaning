package com.example.qgame.repositories;

import com.example.qgame.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("FROM User")
    User first();

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
