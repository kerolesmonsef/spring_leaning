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
import org.springframework.stereotype.Component;

@Getter
@Component
abstract public class IPaymentGateway {
    @Setter
    protected IPaymentService service;
    @Setter
    protected User user;

    protected PaymentMethod paymentMethod;

    protected PaymentService paymentService;


    public IPaymentGateway() {

    }

    public IPaymentGateway(IPaymentService paymentService, User user) {
        this.service = paymentService;
        this.user = user;
        this.paymentMethod = QGameApplication.getBean(PaymentMethodRepository.class).findByName(getName());
        this.paymentService = QGameApplication.getBean(PaymentService.class);

    }

    public final IPaymentResponse gatewayResponse() throws Exception {
        Payment payment = paymentService.create(service, paymentMethod);
        PaymentRepository paymentRepository = QGameApplication.getContext().getBean(PaymentRepository.class);

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
