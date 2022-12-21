package com.example.qgame.repositories;

import com.example.qgame.Models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("FROM PaymentMethod WHERE name = :name")
    PaymentMethod findByName(@Param("name") String name);

    @Query("FROM PaymentMethod P WHERE P.isActive = '1'")
    List<PaymentMethod> getIsActive();
}
