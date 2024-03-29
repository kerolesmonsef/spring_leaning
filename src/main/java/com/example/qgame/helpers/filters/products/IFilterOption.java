package com.example.qgame.helpers.filters.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

@Accessors(chain = true)
public abstract class IFilterOption {

    @Setter
    @Getter
    protected CriteriaBuilder cb;


    @Setter
    protected Root root;

    @Setter
    protected CriteriaQuery criteriaQuery;

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

        return this.getCb().and(lastPredicate, currentPredicate);
    }

    public abstract IFilterOption init(Map<String, Object> property);

}
