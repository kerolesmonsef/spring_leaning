package com.example.qgame.helpers.filters.products;

import com.example.qgame.Models.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;


@Service
public class FilterQueryBuilder {

    @PersistenceContext
    private EntityManager entityManager;


    public FilterQueryBuilderResult buildProductQuery(FilterOptionCollection optionCollection) {

        CriteriaQuery<Product> criteria = entityManager.getCriteriaBuilder().createQuery(Product.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Root<Product> productRoot = criteria.from(Product.class);
        productRoot.alias("pProduct");
        criteria.select(productRoot);

        Predicate predicate = getCumulativePredicate(optionCollection, criteria, cb, productRoot);

        criteria.where(predicate);

        Query query = entityManager.createQuery(criteria);

//        List<Product> products = query.getResultList();

        return new FilterQueryBuilderResult(query, predicate, criteria, productRoot, optionCollection);
    }


    protected Predicate getCumulativePredicate(FilterOptionCollection optionCollection, CriteriaQuery query, CriteriaBuilder cb, Root productRoot) {
        Predicate predicate = null;

        if (optionCollection.isEmpty()) {
            return null;
        }

        for (int i = 0; i < optionCollection.size(); i++) {

            predicate = optionCollection
                    .get(i)
                    .setCb(cb)
                    .setRoot(productRoot)
                    .setCriteriaQuery(query)
                    .cumulatePredicateate(predicate);
        }


        return predicate;
    }


}
