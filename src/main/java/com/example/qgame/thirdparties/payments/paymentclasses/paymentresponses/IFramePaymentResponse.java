package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import com.example.qgame.Models.Payment;

import java.util.List;
import java.util.Map;

public class IFramePaymentResponse extends IPaymentResponse {

    private final String url;

    public IFramePaymentResponse(boolean isSuccess, String apiResponse, String referenceCode, List<String> errors, String url) {
        super(isSuccess, apiResponse, referenceCode, errors);
        this.url = url;
    }

    @Override
    protected Map<String, Object> innerToArray() {

        return Map.ofEntries(
                Map.entry("url", url)
        );
    }

}
