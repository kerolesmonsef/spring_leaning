package com.example.qgame.helpers.filters.impls;

import com.example.qgame.helpers.filters.IFilter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TestWhere extends IFilter {

    @Setter
    @Accessors(chain = true)
    Root r;

    public TestWhere(boolean ask) {
        super(ask);
    }


    @Override
    protected Predicate getPredict() {
        return cb.and(
                cb.or(cb.equal(r.get("title"),"f"),cb.equal(r.get("priceAfterDiscount"),12)),
                cb.or(cb.equal(r.get("description"),"f"),cb.equal(r.get("priceAfterDiscount"),12))
        );
    }
}
