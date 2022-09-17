package com.example.qgame.helpers.filters.products;

import com.example.qgame.Models.Category;
import com.example.qgame.Models.Product;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.filters.products.filterOptionsImpls.PriceFilterOption;
import com.example.qgame.helpers.filters.products.results.CategoryIdResult;
import com.example.qgame.helpers.filters.products.results.MinMaxResult;
import com.example.qgame.repositories.CategoryRepository;
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
        return Arrays.asList(
                getCategoriesId()
        );
    }

    //--------------------------------------------------------
    private Map<String, Object> getCategoriesId() {

        List<CategoryIdResult> categoriesIds;

        if (filterQueryBuilderResult.getFilterOptionCollection().hasType("category")){
           categoriesIds =  QGameApplication.getContext().getBean(CategoryRepository.class).getIdAndName();
        }else{
            categoriesIds = getCategoriesFilterIds();
        }


        return Map.ofEntries(
                Map.entry("name", "category"),
                Map.entry("categoriesIds", categoriesIds)
        );
    }

    private List<CategoryIdResult> getCategoriesFilterIds(){
        CriteriaQuery<Product> productCriteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productRoot.join("category");

        productCriteriaQuery.select(productRoot.get("category"))
                .where(filterQueryBuilderResult.getPredicate()).groupBy(productRoot.get("category_id"));

        TypedQuery<Category> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        return typedQuery.getResultList().stream().map(c -> new CategoryIdResult(c.getId(), c.getName())).toList();
    }
}
