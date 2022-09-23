package com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaymentInfoItem {
    private int quantity;
    private float totalPrice;
    private float price;
    private String name;
    private String productId;
    private String description;
    private String imageUrl;
}
