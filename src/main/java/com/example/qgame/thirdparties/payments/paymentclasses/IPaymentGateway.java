package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.PaymentMethodRepository;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.services.PaymentService;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope("prototype")
abstract public class IPaymentGateway {
    protected IPaymentService service;
    protected User user;

    @Autowired
    private PaymentRepository paymentRepository;

    public IPaymentGateway(IPaymentService paymentService, User user) {
        this.service = paymentService;
        this.user = user;
    }

    public final IPaymentResponse gatewayResponse(Payment payment) throws Exception {

        IPaymentResponse response = innerGatewayResponse(payment);

        payment
                .setPaymentUrl(response.getUrl())
                .setReferenceCode(response.getReferenceCode())
                .setThirdPartyResponse(response.getApiResponse());

        if (this instanceof ICreditPaymentGateway && response.isSuccess()) {
            payment.setSuccess(true);
            // handle payment now
        }

        paymentRepository.save(payment);

        return response;
    }


    protected abstract IPaymentResponse innerGatewayResponse(Payment payment) throws Exception;

    public abstract String getName(); // database name
}
