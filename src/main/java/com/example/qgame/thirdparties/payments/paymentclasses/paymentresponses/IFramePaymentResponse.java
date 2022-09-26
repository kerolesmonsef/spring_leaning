package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import java.util.Map;

public class IFramePaymentResponse extends IPaymentResponse {

    public IFramePaymentResponse(Map<String, Object> map) {
        super(map);
    }

    @Override
    protected Map<String, Object> innerToArray() {

        return Map.ofEntries();
    }

}
