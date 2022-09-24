package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.User;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

abstract public class ICreditPaymentGateway extends IPaymentGateway {
    public ICreditPaymentGateway(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }
}
