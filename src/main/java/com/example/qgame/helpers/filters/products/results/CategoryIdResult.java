package com.example.qgame.helpers.filters.products.results;

import lombok.Getter;

@Getter
public class CategoryIdResult {
    private final Long id;
    private final String name;

    public CategoryIdResult(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
