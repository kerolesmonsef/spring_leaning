package com.example.qgame.requests;

import com.example.qgame.validations.Exists;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Component
public class CreateOrderRequest {


    @Valid
    @NotEmpty
    private List<OrderItemRequest> orderItemRequests ;

    @NotNull(message = "PaymentMethod Must Not Be Null")
    @Exists(entity = "PaymentMethod", column = "id")
    private Long paymentMethodId;

    private String note; // check in the order (orderDescriptor) please

}
