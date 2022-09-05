package com.example.qgame.helpers.filters.impls;

import com.example.qgame.helpers.filters.IFilter;

import javax.persistence.criteria.Path;

public class WhereLike extends IFilter {

    private Path<String> path;
    private String keyword;

    public WhereLike(Path<String> path, String keyword, boolean askCheck) {
        super(askCheck);
        this.path = path;
        this.keyword = keyword;
    }

    @Override
    protected void filter() {
        this.criteriaQuery.where(cb.or(
                cb.like(path, keyword),
                cb.like(path, keyword)
        ));
    }
}
