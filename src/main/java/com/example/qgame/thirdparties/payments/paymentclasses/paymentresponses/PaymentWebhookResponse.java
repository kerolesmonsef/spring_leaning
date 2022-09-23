package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import com.example.qgame.Models.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentWebhookResponse {
    private Payment payment;
    private boolean isSuccess;

    public boolean isFail() {
        return !isSuccess();
    }
}
