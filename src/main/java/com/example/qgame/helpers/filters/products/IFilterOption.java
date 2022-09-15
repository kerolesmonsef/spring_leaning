package com.example.qgame.helpers.filters.products;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Map;

public abstract class IFilterOption {

    private CriteriaBuilder cb;

    public abstract String getName();

    protected abstract Predicate getPredict();

    public final Predicate execute(Predicate lastPredicate) {
        if (lastPredicate == null) {
            return getPredict();
        }

        return cb.and(
                lastPredicate,
                getPredict()
        );
    }
}
