package com.example.qgame.thirdparties.payments.apis;

import com.example.qgame.thirdparties.payments.paymentclasses.IFramePaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.IPaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;

public class OpayVisaMasterCard extends IFramePaymentGateway {

    @Override
    protected PaymentWebhookResponse innerWebhook() {
        return null;
    }
}
