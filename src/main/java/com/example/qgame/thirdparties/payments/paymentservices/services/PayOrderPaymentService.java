package com.example.qgame.thirdparties.payments.paymentservices.services;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.Product;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.dto.OrderItemDto;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.repositories.PaymentRepository;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfoItem;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;

import java.util.Map;
import java.util.UUID;

public class PayOrderPaymentService extends IPaymentService {
    private final User user;
    private final OrderDescriptor orderDescriptor;

    public PayOrderPaymentService(PaymentMethod paymentMethod, User user, OrderDescriptor orderDescriptor) {
        super(paymentMethod);
        this.user = user;
        this.orderDescriptor = orderDescriptor;
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
                Map.entry("total", orderDescriptor.productsPriceAfterDiscount()),
                Map.entry("taxes", orderDescriptor.getTaxes())
        ));

        for (OrderItemDto orderItem : orderDescriptor.getOrderItems()) {
            Product product = orderItem.getProduct();
            paymentInfo.addItem(
                    new PaymentInfoItem()
                            .setDescription(product.getDescription())
                            .setImageUrl(product.firstImageUrl())
                            .setName(product.getTitle())
                            .setPrice(orderItem.price())
                            .setProductId(product.getId().toString())
                            .setQuantity(orderItem.getQuantity())
                            .setTotalPrice(orderItem.price()) // productsPriceAfterDiscount * quantity
            );
        }


        return paymentInfo;
    }

    @Override
    public Payment createPayment() {

        Payment payment = new Payment();
        PaymentInfo paymentInfo = getPaymentInfo();

        payment
                .setCode(UUID.randomUUID().toString())
                .setUser(user)
                .setInformation(paymentInfo)
                .setPaymentService(getName())
                .setAmount(paymentInfo.getTotal())
                .setPaymentMethod(paymentMethod);

        QGameApplication.getContext().getBean(PaymentRepository.class).save(payment);

        return payment;
    }
}
