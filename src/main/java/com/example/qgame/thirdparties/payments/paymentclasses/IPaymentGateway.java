package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.PaymentMethodRepository;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import lombok.Getter;

@Getter
abstract public class IPaymentGateway {
    protected IPaymentService paymentService;
    protected User user;
    protected PaymentMethod paymentMethod;

    public IPaymentGateway(IPaymentService paymentService, User user) {
        this.paymentService = paymentService;
        this.user = user;
        this.paymentMethod = QGameApplication.getContext().getBean(PaymentMethodRepository.class).findByName(getName());
    }

    public final IPaymentResponse gatewayResponse() throws Exception {
        Payment payment = paymentService.createPayment();
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
