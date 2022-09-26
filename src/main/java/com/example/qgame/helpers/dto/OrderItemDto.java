package com.example.qgame.helpers.dto;

import com.example.qgame.Models.Product;
import com.example.qgame.QGameApplication;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.validations.Exists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
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

    @JsonSetter("productId")
    public void setProductId(Long productId){
        this.productId = productId;
        this.product = QGameApplication.getContext().getBean(ProductRepository.class).findById(productId).orElse(null);
    }
}
