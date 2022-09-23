package com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class PaymentInfo {
    private float total;
    private Map<String, Object> data;

    @Getter
    private List<PaymentInfoItem> items;

    public PaymentInfo(Map<String, Object> data) {
        this.data = data;
        total = (float)data.get("total");
    }

    public PaymentInfo addItem(PaymentInfoItem item) {
        this.items.add(item);
        return this;
    }
}
