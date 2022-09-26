package com.example.qgame.requests;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.dto.OrderItemDto;
import com.example.qgame.repositories.PaymentMethodRepository;
import com.example.qgame.validations.Exists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequest {

    @Valid
    @NotEmpty
    private List<OrderItemDto> orderItems;

    @NotNull
    @Exists(entity = "PaymentMethod", column = "id")
    private Long paymentMethodId;

    @JsonIgnore
    private PaymentMethod paymentMethod;

    private String note; // check in the order (orderDescriptor) please

    @JsonSetter("paymentMethodId")
    private void paymentMethodIdSetter(Long paymentMethodId) {
        this.paymentMethod = QGameApplication.getContext().getBean(PaymentMethodRepository.class).findById(paymentMethodId).orElse(null);
        this.paymentMethodId = paymentMethodId;
    }
}