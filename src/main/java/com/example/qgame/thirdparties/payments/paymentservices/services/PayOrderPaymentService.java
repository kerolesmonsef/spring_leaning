package com.example.qgame.thirdparties.payments.paymentservices.services;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfoItem;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

import java.util.Map;

public class PayOrderPaymentService extends IPaymentService {
    private final User user;

    public PayOrderPaymentService(PaymentMethod paymentMethod, User user) {
        super(paymentMethod);
        this.user = user;
    }

    @Override
    public void handle() {

    }

    @Override
    public String getName() {
        return "pay-order";
    }

    @Override
    public PaymentInfo getPaymentInfo() {

        PaymentInfo paymentInfo = new PaymentInfo(Map.ofEntries(
                Map.entry("user_id", user.getId()),
                Map.entry("total", 12f),
                Map.entry("taxes", 12f)
        ));


        paymentInfo.addItem(
                new PaymentInfoItem()
                .setDescription("new product")
                .setImageUrl("no url")
                .setName("product")
                .setPrice(12)
                .setProductId("$#F")
                .setQuantity(3)
                .setTotalPrice(12*3)
        );

        return paymentInfo;
    }

    @Override
    public Payment createPayment() {

        Payment payment = new Payment();
        PaymentInfo paymentInfo = getPaymentInfo();

        payment
                .setUser(user)
                .setInformation(paymentInfo)
                .setPaymentService(getName())
                .setAmount(paymentInfo.getTotal())
                .setPaymentMethod(paymentMethod);

        QGameApplication.getContext().getBean(PaymentRepository.class).save(payment);

        return payment;
    }
}
