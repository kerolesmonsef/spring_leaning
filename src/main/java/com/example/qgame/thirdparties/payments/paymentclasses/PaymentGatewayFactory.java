package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;

public class PaymentGatewayFactory {
    public static IPaymentGateway create(PaymentMethod paymentMethod, User user) {
        IPaymentGateway iPaymentGateway = QGameApplication.getContext().getBean("opay-visa", IPaymentGateway.class);



        return iPaymentGateway;
    }
}
