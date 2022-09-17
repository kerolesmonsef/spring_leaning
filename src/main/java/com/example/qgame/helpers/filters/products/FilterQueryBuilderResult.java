package com.example.qgame.helpers.filters.products;

import lombok.Getter;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
public class FilterQueryBuilderResult<T> {
    private Query query;
    private Predicate predicate;
    private CriteriaQuery criteria;
    private Root<T> productRoot;
    private FilterOptionCollection filterOptionCollection;

    FilterQueryBuilderResult(Query query, Predicate predicate, CriteriaQuery criteria,Root<T> productRoot,FilterOptionCollection FilterOptionCollection){
        this.query = query;
        this.predicate = predicate;
        this.criteria = criteria;
        this.productRoot = productRoot;
        filterOptionCollection = FilterOptionCollection;
    }


}
