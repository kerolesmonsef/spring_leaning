package com.example.qgame.helpers.filters.products;

import com.example.qgame.Models.Category;
import com.example.qgame.Models.Product;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.filters.products.filterOptionsImpls.PriceFilterOption;
import com.example.qgame.helpers.filters.products.results.MinMaxResult;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FilterOptionFilter {

    private FilterQueryBuilderResult<Product> filterQueryBuilderResult;
    private EntityManager entityManager;
    private EntityManager em;
    private CriteriaBuilder cb;
    private Root<Object> productRoot;

    public FilterOptionFilter(FilterQueryBuilderResult filterQueryBuilderResult) {
        this.filterQueryBuilderResult = filterQueryBuilderResult;
        this.entityManager = em = QGameApplication.getContext().getBean(EntityManager.class);
        this.cb = entityManager.getCriteriaBuilder();
        this.productRoot = filterQueryBuilderResult.getProductRoot();
    }


    public List<Map<String, Object>> getOptions() {
        return Arrays.asList(getCategoriesId());
    }

    //--------------------------------------------------------
    private Map<String, Object> getCategoriesId() {

        CriteriaQuery<Category> categoryCriteriaQuery = cb.createQuery(Category.class);
        Root<Category> cat = categoryCriteriaQuery.from(Category.class);

        Subquery<Integer> subquery = categoryCriteriaQuery.subquery(Integer.class);
        Root product = subquery.from(Product.class);

//        CriteriaQuery subquery = filterQueryBuilderResult.getCriteria();
//        Root<Product> product = filterQueryBuilderResult.getProductRoot();

        subquery.select(product.get("id")).where(cb.equal(product.get("title"),"shit"));

        categoryCriteriaQuery.select(cat).where(cb.in(cat.get("id")).value(subquery));

        TypedQuery<Category> q = em.createQuery(categoryCriteriaQuery);
        List<Category> categories = q.getResultList();
        System.out.println(categories);


        return null;
    }
}
