package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.products.IFilterOption;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

@Component
public class CategoryFilterOption extends IFilterOption {
    private List<Long> categoriesIds;


    @Override
    public String getName() {
        return "category";// + (categoriesIds.get(0));
    }

    @Override
    public Predicate getPredicate() {
        return root.get("category_id").in(this.categoriesIds);
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        this.categoriesIds = (List<Long>) property.get("categoriesIds");

        return this;
    }

    @Override
    public Map<String, Object> toResource() {
        return Map.ofEntries(
                Map.entry("name", getName()),
                Map.entry("categoriesIds", categoriesIds)
        );
    }
}
