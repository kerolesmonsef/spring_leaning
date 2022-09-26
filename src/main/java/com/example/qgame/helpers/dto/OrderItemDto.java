package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Product;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.validations.Exists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
//@AllArgsConstructor
public class OrderItemDto {

    @JsonIgnore
    private Product product;

    @NotNull
    @Exists(entity = "Product", column = "id")
    private Long productId;

    @Min(1)
    @NotNull
    private Integer quantity;

    public float price() {
        return product.priceAfterDiscount() * quantity;
    }

    public float priceBeforeDiscount() {
        return singlePrice() * quantity;
    }

    private float singlePrice() {
        return this.product.getPrice();
    }

    public void setProductId(Long productId){
        this.productId = productId;
        System.out.println("aya");
        this.product = QGameApplication.getContext().getBean(ProductRepository.class).findById(productId).orElse(null);
    }
}
