package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.OrderDetail;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.requests.CreateOrderRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

public class OrderCreator {
    private final PaymentMethod paymentMethod;
    private final List<OrderDetail> orderDetails;
    private OrderDescriptor orderDescriptor;
    private final CreateOrderRequest orderRequest;
    private final User user;

    @Accessors(chain = true)
    @Setter
    private OrderService orderService;

    public OrderCreator(CreateOrderRequest request, User user, PaymentMethod paymentMethod, List<OrderDetail> orderDetails) {
        this.orderRequest = request;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.orderDetails = orderDetails;
    }


    public Order create() {
        return orderService.store(getOrderDescriptor());
    }


    public OrderDescriptor getOrderDescriptor() {
        if (orderDescriptor != null) {
            return orderDescriptor;
        }
        return orderDescriptor = new OrderDescriptor()
                .setPaymentMethod(paymentMethod)
                .setUser(user)
                .setOrderDetails(orderDetails)
                .setNote(orderRequest.getNote());
    }

}
