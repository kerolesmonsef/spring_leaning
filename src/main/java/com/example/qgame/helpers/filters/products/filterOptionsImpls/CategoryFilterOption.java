package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.IFilter;
import com.example.qgame.helpers.filters.products.IFilterOption;

import javax.persistence.criteria.Predicate;

public class CategoryFilterOption extends IFilterOption {

    @Override
    public String getName() {
        return "category";
    }

    @Override
    protected Predicate getPredict() {
        return null;
    }
}
