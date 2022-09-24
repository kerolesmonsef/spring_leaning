package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderItemDto {

    @JsonIgnore
    private Product product;

    @NotNull
    private Long product_id;

    @NotNull
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
