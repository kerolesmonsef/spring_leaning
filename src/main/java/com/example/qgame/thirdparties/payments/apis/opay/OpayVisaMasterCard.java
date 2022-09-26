package com.example.qgame.thirdparties.payments.apis.opay;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.thirdparties.payments.paymentclasses.IFramePaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.IPaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component(value = "opay-visa")
public class OpayVisaMasterCard extends IOpayFramePaymentGateway {

    @Override
    protected String getPayMethod() {
        return "BankCard";
    }

    @Override
    protected String getReturnUrl() {
        return "https://google.com";
    }


    @Override
    public String getName() {
        return "opay-visa";
    }
}
