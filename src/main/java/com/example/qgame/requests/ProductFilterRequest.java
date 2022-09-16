package com.example.qgame.requests;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductFilterRequest {
    private List<Map<String,Object>> properties;
}
