package com.example.qgame.helpers.filters.products.results;

import lombok.Getter;

@Getter
public class MinMaxResult {
    private final Double min;
    private final Double max;

    public MinMaxResult(Double min, Double max){
        this.min = min;
        this.max = max;
    }
}
