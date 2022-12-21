package com.example.qgame.helpers.listeners;

import com.example.qgame.Models.Payment;
import com.example.qgame.helpers.StringHelper;

import javax.persistence.PrePersist;
import java.util.UUID;

public class PaymentListener {

    @PrePersist
    public void beforeCreate(Payment payment){
//        payment.setCode(UUID.randomUUID().toString());
        payment.setCode(StringHelper.getRandomTicket());
    }
}
