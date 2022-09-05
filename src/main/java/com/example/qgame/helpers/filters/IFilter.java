package com.example.qgame.helpers.filters;

import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class IFilter {
    @Setter
    protected CriteriaQuery criteriaQuery;

    @Setter
    protected CriteriaBuilder cb;

    protected boolean askCheck;

    public IFilter(boolean askCheck) {
        this.askCheck = askCheck;
    }



    public abstract void filter();
}
