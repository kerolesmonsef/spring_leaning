package com.example.qgame.services;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment create(IPaymentService paymentService, PaymentMethod paymentMethod) {
        Payment payment = new Payment();
        PaymentInfo paymentInfo = paymentService.getPaymentInfo();

        payment
                .setUser(paymentService.getUser())
                .setInformation(paymentInfo)
                .setPaymentService(paymentService.getName())
                .setAmount(paymentInfo.getTotal())
                .setPaymentMethod(paymentMethod);

        paymentRepository.save(payment);

        return payment;
    }
}
