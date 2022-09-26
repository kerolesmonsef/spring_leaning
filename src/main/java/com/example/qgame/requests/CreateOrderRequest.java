package com.example.qgame.requests;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.converters.PaymentMethodDeserializer;
import com.example.qgame.helpers.dto.OrderItemDto;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.repositories.PaymentMethodRepository;
import com.example.qgame.validations.Exists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrderRequest {

    @NotNull
    private List<OrderItemDto> orderItems;

    @NotNull
    @Exists(entity = "PaymentMethod", column = "id")
    private Long payment_method_id;

//    @JsonDeserialize(using = PaymentMethodDeserializer.class)
    @JsonIgnore
    private PaymentMethod paymentMethod;


    private String note; // check in the order (orderDescriptor) please

    @JsonSetter("payment_method_id")
    private void unpackNested(Long payment_method_id) {
        this.paymentMethod = QGameApplication.getContext().getBean(PaymentMethodRepository.class).findById(payment_method_id).orElse(null);
        this.payment_method_id = payment_method_id;
    }
}
