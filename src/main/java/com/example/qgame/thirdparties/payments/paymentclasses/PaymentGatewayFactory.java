package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.thirdparties.payments.apis.opay.OpayVisaMasterCard;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

public class PaymentGatewayFactory {
    public static IPaymentGateway create(PaymentMethod paymentMethod, User user, IPaymentService paymentService) {
        IPaymentGateway iPaymentGateway = (IPaymentGateway) QGameApplication
                .getContext()
                .getBean(paymentMethod.getClassName(), paymentService, user);


        iPaymentGateway.setService(paymentService);
        iPaymentGateway.setUser(user);

        return iPaymentGateway;
    }
}
