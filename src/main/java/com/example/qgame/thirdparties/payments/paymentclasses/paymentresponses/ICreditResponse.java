package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import java.util.List;
import java.util.Map;

public class ICreditResponse extends IPaymentResponse {

    public ICreditResponse(boolean isSuccess, String apiResponse, List<String> errors) {
        super(isSuccess, apiResponse, errors);
    }

    @Override
    protected Map<String, Object> innerToArray() {
        return Map.ofEntries();
    }
}
