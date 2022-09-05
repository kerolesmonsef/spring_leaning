package com.example.qgame.helpers.filters;

import com.example.qgame.Models.User;
import com.example.qgame.helpers.filters.impls.Where;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {
    private List<IFilter> filters = new ArrayList<>();

    @Setter
    @Accessors(chain = true)
    private EntityManager entityManager;

    public FilterBuilder addFilter(IFilter filter) {
        filters.add(filter);
        return this;
    }


    public void build() {

    }


    public void main(String[] args) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteria = cb.createQuery();
        Root u = criteria.from(User.class);
        u.get("name");
        criteria.multiselect(u).where().where().orderBy().where();


        FilterBuilder filterBuilder = new FilterBuilder()
                .setEntityManager(entityManager)
                .addFilter(new Where(u.get("name"), "keroles", true));

    }
}
