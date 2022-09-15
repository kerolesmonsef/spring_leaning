package com.example.qgame.helpers.filters.products;

import javax.persistence.criteria.Predicate;

public abstract class IFilterOption {
    public abstract String getName();

    protected abstract Predicate getPredict();
}
