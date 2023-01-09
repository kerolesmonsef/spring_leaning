package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.Models.Option;
import com.example.qgame.Models.ProductOptionValue;
import com.example.qgame.helpers.filters.products.IFilterOption;
import com.example.qgame.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class OptionValueFilterOption extends IFilterOption {
    @Autowired
    private OptionRepository optionRepository;

    private String optionName;
    private List<String> values;


    @Override
    public String getName() {
        return "option";
    }

    @Override
    public Predicate getPredicate() {
        Option option = optionRepository.findByTitle(optionName).orElseThrow();
        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<ProductOptionValue> root = subquery.from(ProductOptionValue.class);
        subquery.select(root.get("id"));
        subquery.where(
                cb.and(
                        cb.equal(root.get("option").get("id"), option.getId()),
                        root.get("value").in(values),
                        cb.equal(root.get("product").get("id"), this.root.get("id"))
                )
        );

        return cb.exists(subquery);
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        optionName = property.get("optionName").toString();
        values = (List<String>) property.get("values");

        return this;
    }
}
