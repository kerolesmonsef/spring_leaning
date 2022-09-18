package com.example.qgame.helpers.dto;

import lombok.Getter;

@Getter
public class MinMaxResult {
    private final Float min;
    private final Float max;

    public MinMaxResult(Float min, Float max){
        this.min = min;
        this.max = max;
    }
}
