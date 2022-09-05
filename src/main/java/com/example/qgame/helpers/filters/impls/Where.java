package com.example.qgame.helpers.filters.impls;

import com.example.qgame.helpers.filters.IFilter;

import javax.persistence.criteria.Path;

public class Where extends IFilter {

    private Path column;
    private String value;

    public Where(Path column, String value, boolean check) {
        super(check);

        this.column = column;
        this.value = value;
    }

    @Override
    public void filter() {

    }
}
