package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OrderItemDto {
    private Product product;
    private int quantity;

    public float price() {
        return product.priceAfterDiscount() * quantity;
    }

    public float priceBeforeDiscount() {
        return singlePrice() * quantity;
    }

    private float singlePrice() {
        return this.product.getPrice();
    }
}
