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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "opay-visa")
@Scope("prototype")
public class OpayVisaMasterCard extends IOpayFramePaymentGateway {

    public OpayVisaMasterCard(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }

    @Override
    protected String getPayMethod() {
        return "BankCard";
    }


    @Override
    public String getName() {
        return "opay-visa";
    }
}
