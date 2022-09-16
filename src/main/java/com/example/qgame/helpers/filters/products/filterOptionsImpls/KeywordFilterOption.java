package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.products.IFilterOption;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.Map;

@Component
public class KeywordFilterOption extends IFilterOption {

    private String keyword;
    private String match;


    @Override
    public String getName() {
        return "keyword";
    }

    @Override
    public Predicate getPredicate() {
        return cb.and(
                cb.or(
                        cb.like(root.get("title"), match),
                        cb.like(root.get("description"), match)
                )
        );
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        keyword = (String) property.get("keyword");
        match = "%" + keyword + "%";
        return this;
    }

    @Override
    public Map<String, Object> toResource() {
        return Map.ofEntries(
                Map.entry("name", getName()),
                Map.entry("keyword", keyword)
        );
    }
}
