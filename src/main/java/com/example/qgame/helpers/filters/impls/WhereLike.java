package com.example.qgame.helpers.filters.impls;

import com.example.qgame.helpers.filters.IFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WhereLike extends IFilter {

    private Path<String> path;
    private String keyword;

    public WhereLike(Path<String> path, String keyword, boolean askCheck) {
        super(askCheck);
        this.path = path;
        this.keyword = keyword;
    }


    @Override
    protected Predicate getPredict() {
        return cb.like(path, "%" + keyword + "%");
    }
}
