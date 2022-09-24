package com.example.qgame.helpers.order;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.dto.OrderItemDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDescriptor {

    private List<OrderItemDto> orderItems;
    private User user;
    private String coupon;
    private String note;
    private PaymentMethod paymentMethod;

    public OrderDescriptor(List<OrderItemDto> orderItems, User user, PaymentMethod paymentMethod) {
        this.orderItems = orderItems;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public OrderDescriptor() {

    }

    public float productsPrice() {
        float total = 0;

        for (OrderItemDto orderItem : orderItems) {
            total += orderItem.priceBeforeDiscount();
        }

        return total;
    }

    public float productsPriceAfterDiscount() {
        float total = 0;

        for (OrderItemDto orderItem : orderItems) {
            total += orderItem.price();
        }

        return total;
    }

    public float totalPrice() {
        return productsPriceAfterDiscount() + getTaxes();
    }

    public float getTaxes() {
        return 0;
    }
}
