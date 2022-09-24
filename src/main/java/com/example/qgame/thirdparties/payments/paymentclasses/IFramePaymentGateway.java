package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

abstract public class IFramePaymentGateway extends IPaymentGateway {
    public IFramePaymentGateway(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }

    public final PaymentWebhookResponse webhook() {
        PaymentWebhookResponse response = innerWebhook();
        Payment payment = response.getPayment();

        if (response.isFail()){
            // update payment to fail if not null
        }else{ // success then handel the service
            // update payment to success
            // handle the service
        }

        return response;
    }


    protected abstract PaymentWebhookResponse innerWebhook();
}
