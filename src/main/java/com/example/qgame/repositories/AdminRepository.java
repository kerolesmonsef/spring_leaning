package com.example.qgame.repositories;

import com.example.qgame.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("select a from Admin a where a.email= ?1")
    Optional<Admin> findByEmail(String email);
}
