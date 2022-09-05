package com.example.qgame.helpers.filters;

import com.example.qgame.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {
    private List<IFilter> filters = new ArrayList<>();
    private EntityManager entityManager;
    private CriteriaBuilder cb;
    private final CriteriaQuery<Object> criteriaQuery;

    public FilterBuilder(CriteriaQuery<Object> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public FilterBuilder(CriteriaQuery<Object> criteriaQuery, EntityManager entityManager) {
        this.criteriaQuery = criteriaQuery;
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
    }

    public FilterBuilder setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
        return this;
    }


    public FilterBuilder addFilter(IFilter filter) {
        filters.add(filter.setCb(cb));
        return this;
    }


    public CriteriaQuery<Object> build() {
//        this.filters.forEach((getPredict) -> {
//            getPredict.setCb(cb).execute();
//        });

        if (filters.size() == 0) {
            return criteriaQuery;
        }


        Predicate lastPredicate = filters.get(0).getPredict();

        for (int i = 1; i < filters.size(); i++) {
            lastPredicate = cb.and(
                    lastPredicate,
                    filters.get(i).getPredict()
            );
        }

        return criteriaQuery.where(lastPredicate);
    }

    public Query buildQuery() {
        CriteriaQuery<Object> criteriaQuery = this.build();

        return entityManager.createQuery(criteriaQuery);
    }


    public void main(String[] args) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteria = cb.createQuery();
        Root u = criteria.from(User.class);
        criteria.select(u).where().where().orderBy().where();


//        FilterBuilder filterBuilder = new FilterBuilder()
//                .setEntityManager(entityManager)
//                .addFilter(new Where(u.get("name"), "keroles", true));

    }
}
