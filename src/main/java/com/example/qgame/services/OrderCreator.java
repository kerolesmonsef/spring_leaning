package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.User;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.requests.CreateOrderRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletRequest;

public class OrderCreator {
    private OrderDescriptor orderDescriptor;
    private final CreateOrderRequest orderRequest;
    private final User user;

    @Accessors(chain = true)
    @Setter
    private OrderService orderService;

    public OrderCreator(CreateOrderRequest request, User user) {
        this.orderRequest = request;
        this.user = user;
    }


    public Order create() {
        return orderService.store(getOrderDescriptor());
    }


    public OrderDescriptor getOrderDescriptor() {
        if (orderDescriptor != null) {
            return orderDescriptor;
        }
        return orderDescriptor = new OrderDescriptor()
                .setPaymentMethod(orderRequest.getPaymentMethod())
                .setUser(user)
                .setOrderItems(orderRequest.getOrderItems())
                .setNote(orderRequest.getNote());
    }

}
