package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.thirdparties.payments.apis.opay.OpayVisaMasterCard;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayFactory {
    public final ApplicationContext context;

    public PaymentGatewayFactory(ApplicationContext context) {
        this.context = context;
    }

    public IPaymentGateway create(String paymentGatewayName, User user, IPaymentService paymentService) {
        return (IPaymentGateway) this
                .context
                .getBean(paymentGatewayName, paymentService, user);

    }
}
