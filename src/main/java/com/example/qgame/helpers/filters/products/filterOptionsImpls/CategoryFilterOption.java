package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.Models.Category;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.filters.IFilter;
import com.example.qgame.helpers.filters.products.IFilterOption;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public class CategoryFilterOption extends IFilterOption {

    private List<Long> ids;


    public CategoryFilterOption(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String getName() {
        return "category";
    }

    @Override
    protected Predicate getPredict() {
        return null;
    }
}
