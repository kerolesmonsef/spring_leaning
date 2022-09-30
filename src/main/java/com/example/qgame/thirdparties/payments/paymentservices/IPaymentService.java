package com.example.qgame.thirdparties.payments.paymentservices;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.User;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import lombok.Getter;

@Getter
abstract public class IPaymentService {
    @Getter
    protected final User user;

    protected IPaymentService(User user) {
        this.user = user;
    }

    public abstract void handle();

    public abstract String getName();

    public abstract PaymentInfo getPaymentInfo();
}
