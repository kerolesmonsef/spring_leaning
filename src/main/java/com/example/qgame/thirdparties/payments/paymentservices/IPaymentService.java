package com.example.qgame.thirdparties.payments.paymentservices;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
abstract public class IPaymentService {

    protected PaymentMethod paymentMethod;

    public abstract void handle();

    public abstract String getName();

    public abstract PaymentInfo getPaymentInfo();

    public abstract Payment createPayment();
}
