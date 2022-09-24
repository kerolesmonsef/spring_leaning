package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import com.example.qgame.Models.Payment;

import java.util.List;
import java.util.Map;

public class IFramePaymentResponse extends IPaymentResponse {

    private String url;


    @Override
    protected Map<String, Object> innerToArray() {

        return Map.ofEntries(
                Map.entry("url", url)
        );
    }

}
