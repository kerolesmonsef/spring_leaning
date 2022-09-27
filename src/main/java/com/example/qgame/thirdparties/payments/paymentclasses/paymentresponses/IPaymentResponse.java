package com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses;

import lombok.Data;
import lombok.experimental.Accessors;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
abstract public class IPaymentResponse {
    @Accessors(fluent = true)
    private Boolean isSuccess;
    private String referenceCode;
    private List<String> errors;
    private String apiResponse;
    private String url;

    public IPaymentResponse(Map<String, Object> map) {
        init(map);
    }

    protected abstract Map<String, Object> innerToResource();


    public final Map<String, Object> toResource() {
        Map<String, Object> map = new HashMap<>() {{
            put("isSuccess", isSuccess);
            put("errors", errors);
            put("url", url);
        }};


        map.putAll(innerToResource());

        return map;
    }

    private void init(Map<String ,Object> map){
        isSuccess = (Boolean) map.get("isSuccess");
        referenceCode = (String) map.get("referenceCode");

        if (map.containsKey("errors")) {
            errors = (List<String>) map.get("errors");
        } else {
            errors = new ArrayList<>();
        }

        apiResponse = (String) map.get("apiResponse");
        url = (String) map.get("url");
    }
}
