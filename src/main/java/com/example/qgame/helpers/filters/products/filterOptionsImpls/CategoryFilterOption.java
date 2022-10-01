package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.products.IFilterOption;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

@Component
public class CategoryFilterOption extends IFilterOption {
    private List<Long> values;


    @Override
    public String getName() {
        return "category";// + (values.get(0));
    }

    @Override
    public Predicate getPredicate() {
        return root.get("category").get("id").in(this.values);
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        this.values = (List<Long>) property.get("values");

        return this;
    }
}
