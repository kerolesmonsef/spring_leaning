package com.example.qgame.repositories;

import com.example.qgame.Models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("FROM PaymentMethod WHERE name = :name")
    PaymentMethod findByName(@Param("name") String name);
}
