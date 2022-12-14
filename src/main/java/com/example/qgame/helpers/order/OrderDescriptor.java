package com.example.qgame.helpers.order;

import com.example.qgame.Models.OrderDetail;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDescriptor {

    private List<OrderDetail> orderDetails;
    private User user;
    private String coupon;
    private String note;
    private PaymentMethod paymentMethod;

    public OrderDescriptor(List<OrderDetail> orderDetails, User user, PaymentMethod paymentMethod) {
        this.orderDetails = orderDetails;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public OrderDescriptor() {

    }

    public float productsPrice() {
        return orderDetails.stream()
                .map(OrderDetail::priceBeforeDiscount)
                .reduce(0.0f, Float::sum);
    }

    public float productsPriceAfterDiscount() {
        return orderDetails.stream()
                .map(OrderDetail::priceAfterDiscount)
                .reduce(0.0f, Float::sum);

    }

    public float totalPrice() {
        return productsPriceAfterDiscount() + getTaxes();
    }

    public float getTaxes() {
        return 0;
    }
}
