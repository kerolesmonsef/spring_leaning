package com.example.qgame.thirdparties.payments.paymentclasses;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import lombok.Getter;

@Getter
abstract public class IPaymentGateway {
    private IPaymentService paymentService;
    private User user;
    private PaymentMethod paymentMethod;

    public IPaymentGateway(IPaymentService paymentService, User user, PaymentMethod paymentMethod) {
        this.paymentService = paymentService;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public final IPaymentResponse gatewayResponse() {
        Payment payment = paymentService.createPayment();
        PaymentRepository paymentRepository = QGameApplication.getContext().getBean(PaymentRepository.class);

        IPaymentResponse response = innerGatewayResponse(payment);


//        $payment_update_array = [
//                'payment_url' => $response->url(),
//                'reference_code' => $response->getReferenceCode(),
//                'third_party_response' => is_string($response->apiResponse()) ? $response->apiResponse() : json_encode($response->apiResponse()),
//        ];

        if (this instanceof ICreditPaymentGateway && response.isSuccess()) {
            payment.setSuccess(true);
//        $out = get_class($this->paymentService)::handle($payment);
//        $payment_update_array['status'] = "success";
//        $response->setExtraOut($out);
        }

        paymentRepository.save(payment);

        return response;
    }


    protected abstract IPaymentResponse innerGatewayResponse(Payment payment);

    public abstract String getName();
}
