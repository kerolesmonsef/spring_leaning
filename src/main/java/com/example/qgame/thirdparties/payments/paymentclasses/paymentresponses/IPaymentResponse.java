package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import com.example.qgame.Models.Payment;
import com.example.qgame.thirdparties.payments.paymentclasses.IPaymentGateway;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;


import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
abstract public class IPaymentResponse {
    private boolean isSuccess;
    private String referenceCode;
    private List<String> errors;
    private String apiResponse;
    private String url;


    protected abstract Map<String, Object> innerToArray();


    public final Map<String, Object> toArray() {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("isSuccess", isSuccess),
                Map.entry("errors", errors)
        );

        map.putAll(innerToArray());

        return map;
    }
}
