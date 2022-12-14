package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.products.IFilterOption;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.Map;

@Component
public class PriceFilterOption extends IFilterOption {

    private Double from;
    private Double to;

    public PriceFilterOption(){

    }

    public PriceFilterOption(Double from,Double to){
        this.from = from;
        this.to = to;
    }

    @Override
    public String getName() {
        return "priceAfterDiscount";
    }

    @Override
    public Predicate getPredicate() {
        return cb.between(root.get("priceAfterDiscount"), from, to);
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        this.from = Double.parseDouble(property.get("from").toString());
        this.to = Double.parseDouble(property.get("to").toString());
        return this;
    }
}
