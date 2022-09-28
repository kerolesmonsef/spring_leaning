package com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
@Accessors(chain = true)
public class PaymentInfo {
    private float total;
    private Map<String, Object> data;

    @Getter
    private final List<PaymentInfoItem> items = new ArrayList<>();

    public PaymentInfo(Map<String, Object> data) {
        this.data = data;
        total = (float) data.get("total");
    }

    public PaymentInfo addItem(PaymentInfoItem item) {
        this.items.add(item);
        return this;
    }

    public ArrayList<TreeMap> OpayItemsToArray() {
        ArrayList<TreeMap> productList = new ArrayList<>();

        for (PaymentInfoItem pii : items) {
            TreeMap<String, Object> productTreeMap = new TreeMap<>();
            productTreeMap.put("productId", pii.getProductId());
            productTreeMap.put("name", pii.getName());
            productTreeMap.put("description", pii.getName());
            productTreeMap.put("price", pii.getPrice());
            productTreeMap.put("quantity", pii.getQuantity());
            productTreeMap.put("imageUrl", pii.getImageUrl());
            productList.add(productTreeMap);
        }

        return productList;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentInfo other = (PaymentInfo) obj;
        return total == other.total;
    }
}
