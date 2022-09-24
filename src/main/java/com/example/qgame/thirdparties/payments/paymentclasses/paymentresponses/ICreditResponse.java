package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import java.util.List;
import java.util.Map;

public class ICreditResponse extends IPaymentResponse {

    @Override
    protected Map<String, Object> innerToArray() {
        return Map.ofEntries();
    }
}
