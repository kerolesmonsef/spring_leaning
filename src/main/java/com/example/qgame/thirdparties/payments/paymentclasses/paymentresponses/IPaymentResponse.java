package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import com.example.qgame.Models.Payment;
import com.example.qgame.thirdparties.payments.paymentclasses.IPaymentGateway;
import lombok.Getter;


import java.util.List;
import java.util.Map;

@Getter
abstract public class IPaymentResponse {
    private final boolean isSuccess;
    private String referenceCode;
    private List<String> errors;
    private String apiResponse;

    public IPaymentResponse(boolean isSuccess, String apiResponse,String referenceCode, List<String> errors) {
        this.isSuccess = isSuccess;
        this.referenceCode = referenceCode;
        this.errors = errors;
        this.apiResponse = apiResponse;
    }


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
