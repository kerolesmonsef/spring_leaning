package com.example.qgame.requests;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ProductFilterRequest {
    private List<Map<String, Object>> properties;

    public List<Map<String, Object>> getProperties() {
        if (this.properties == null) {
            return new ArrayList<>();
        }

        return this.properties;
    }
}
