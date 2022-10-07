package com.example.qgame.thirdparties.payments.apis.opay;

import com.example.qgame.Models.User;
import com.example.qgame.helpers.Helper;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("wallet")
@Scope("prototype")
public class OpayWallet extends IOpayFramePaymentGateway {


    public OpayWallet(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }

    @Override
    protected String getPayMethod() {
        return "MWALLET";
    }


    @Override
    public String getName() {
        return "wallet";
    }
}