package com.example.qgame.helpers.filters.products.filterOptionsImpls;

import com.example.qgame.helpers.filters.IFilter;
import com.example.qgame.helpers.filters.products.IFilterOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.Map;

@Component
@Scope("prototype")
public class NoopFilterOption extends IFilterOption {

    @Override
    public String getName() {
        return "noop";
    }

    @Override
    public Predicate getPredicate() {
        return cb.equal(cb.literal(1), 1);
    }

    @Override
    public IFilterOption init(Map<String, Object> property) {
        return this;
    }
}
