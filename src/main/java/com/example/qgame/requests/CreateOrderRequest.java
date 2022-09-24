package com.example.qgame.requests;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.helpers.dto.OrderItemDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    private List<OrderItemDto> orderItems;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Blog blog;

    private String note; // check in the order (orderDescriptor) please
}
