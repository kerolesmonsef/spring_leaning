package com.example.qgame.thirdparties.payments.paymentservices.services;

import com.example.qgame.Models.OrderDetail;
import com.example.qgame.Models.Product;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfoItem;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import lombok.Getter;

import java.util.Map;

public class PayOrderPaymentService extends IPaymentService {

    @Getter
    protected final OrderDescriptor orderDescriptor;

    public PayOrderPaymentService(User user, OrderDescriptor orderDescriptor) {
        super(user);
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

        for (OrderDetail orderDetails : orderDescriptor.getOrderDetails()) {
            Product product = orderDetails.getProduct();
            paymentInfo.addItem(
                    new PaymentInfoItem()
                            .setDescription(product.getDescription())
                            .setImageUrl(product.firstImageUrl())
                            .setName(product.getTitle())
                            .setPrice(orderDetails.priceAfterDiscount())
                            .setProductId(product.getId().toString())
                            .setQuantity(orderDetails.getQuantity())
                            .setTotalPrice(orderDetails.priceAfterDiscount()) // productsPriceAfterDiscount * quantity
            );
        }


        return paymentInfo;
    }
}
