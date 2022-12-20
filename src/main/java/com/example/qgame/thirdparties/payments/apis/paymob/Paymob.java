package com.example.qgame.thirdparties.payments.apis.paymob;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.User;
import com.example.qgame.thirdparties.payments.paymentclasses.IFramePaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

public class Paymob extends IFramePaymentGateway {

    public Paymob(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }

    @Override
    protected PaymentWebhookResponse innerWebhook() {


        return null;
    }

    @Override
    protected IPaymentResponse innerGatewayResponse(Payment payment) throws Exception {
        PaymentInfo paymentInfo = this.service.getPaymentInfo();

        float total = paymentInfo.getTotal() * 100;

        System.out.println(total);
        return null;
    }

    @Override
    public String getName() {
        return "paymob";
    }


    private String getAuthinticationToken() {

    }
}
