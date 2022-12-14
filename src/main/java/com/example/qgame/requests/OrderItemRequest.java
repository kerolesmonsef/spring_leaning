package com.example.qgame.requests;

import com.example.qgame.validations.Exists;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemRequest {

    @NotNull
    @Exists(entity = "Product", column = "id")
    private Long productId;


    @Min(1)
    @NotNull
    private Integer quantity;
}
