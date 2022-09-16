package com.example.qgame.helpers.filters.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public abstract class IFilterOption {


    @Setter
    @Accessors(chain = true)
    protected CriteriaBuilder cb;

    @Setter
    @Accessors(chain = true)
    protected Root root;

    public abstract String getName();

    @JsonIgnore
    public abstract Predicate getPredicate();

    @JsonIgnore
    public final Predicate cumulatePredicateate(Predicate lastPredicate) {
        Predicate currentPredicate = getPredicate();

        if (lastPredicate == null) {
            return currentPredicate; // may null
        }


        if (currentPredicate == null) return lastPredicate; // may null

        return cb.and(lastPredicate, currentPredicate);
    }

    public abstract IFilterOption init(Map<String, Object> property);


    public Map<String, Object> toResource() {
        return null;
    }
}
