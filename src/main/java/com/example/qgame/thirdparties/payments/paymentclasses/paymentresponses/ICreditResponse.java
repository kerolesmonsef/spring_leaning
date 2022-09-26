package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import java.util.List;
import java.util.Map;

public class ICreditResponse extends IPaymentResponse {

    public ICreditResponse(Map<String, Object> map) {
        super(map);
    }

    @Override
    protected Map<String, Object> innerToArray() {
        return Map.ofEntries();
    }
}
