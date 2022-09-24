package com.example.qgame.repositories;

import com.example.qgame.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT u FROM ")
//    User findTopByOrOrderByIdAsc();

    @Query("FROM User")
    User first();
}
