package com.example.qgame.helpers.filters.products;

import com.example.qgame.Models.Category;
import com.example.qgame.Models.Option;
import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductOptionValue;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.dto.CategoryIdResult;
import com.example.qgame.helpers.dto.OptionValueDTO;
import com.example.qgame.helpers.dto.OptionValuesDto;
import com.example.qgame.repositories.CategoryRepository;
import org.apache.groovy.util.Maps;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

public class FilterOptionFilter {

    private FilterQueryBuilderResult<Product> filterQueryBuilderResult;
    private EntityManager entityManager;
    private CriteriaBuilder cb;

    public FilterOptionFilter(FilterQueryBuilderResult filterQueryBuilderResult) {
        this.filterQueryBuilderResult = filterQueryBuilderResult;
        this.entityManager = QGameApplication.getContext().getBean(EntityManager.class);
        this.cb = entityManager.getCriteriaBuilder();
    }


    public List<Map<String, Object>> getOptions() {
        return Collections.singletonList(
//                getCategoriesId()
                getOptionValues()
        );
    }

    //--------------------------------------------------------
    private Map<String, Object> getCategoriesId() {

        List<CategoryIdResult> categoriesIds;

        if (filterQueryBuilderResult.getFilterOptionCollection().hasType("category")) {
            categoriesIds = QGameApplication.getContext().getBean(CategoryRepository.class).getIdAndName();
        } else {
            categoriesIds = getCategoriesFilterIds();
        }


        return Map.ofEntries(
                Map.entry("name", "category"),
                Map.entry("categoriesIds", categoriesIds)
        );
    }

    private Map<String, Object> getOptionValues() {
        CriteriaQuery<Tuple> productCriteriaQuery = cb.createQuery(Tuple.class);
        Root<Product> pr = productCriteriaQuery.from(Product.class);
        Root<ProductOptionValue> povr = productCriteriaQuery.from(ProductOptionValue.class);
        Root<Option> or = productCriteriaQuery.from(Option.class);

        productCriteriaQuery.multiselect(pr.get("id").alias("idd"), or.get("title").alias("title"), povr.get("value").alias("value"))
                .where(
                        cb.and(
                                cb.and(filterQueryBuilderResult.getPredicate(), cb.equal(pr.get("id"), povr.get("product").get("id")))
                                ,
                                cb.equal(or.get("id"), povr.get("option").get("id"))
                        )
                ); // group by here

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        List<OptionValueDTO> optionValueDTOS = typedQuery.getResultList().stream().map(t -> new OptionValueDTO(t.get("title").toString(), t.get("value").toString())).toList();

        Map<String, List<String>> optionListValues = new HashMap<>();

        optionValueDTOS.forEach((ov) -> {

            if (optionListValues.containsKey(ov.getOptionName())) {
                optionListValues.put(ov.getOptionName(),Arrays.asList(ov.getValue())).add(ov.getValue());
            } else {
                optionListValues.put(ov.getOptionName(), Arrays.asList(ov.getValue()));
            }
        });

        return Map.ofEntries(
                Map.entry("name", "option"),
                Map.entry("values", optionListValues)
        );
    }

    private List<CategoryIdResult> getCategoriesFilterIds() {
        CriteriaQuery<Product> productCriteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productRoot.join("category");

        productCriteriaQuery.select(productRoot.get("category"))
                .where(filterQueryBuilderResult.getPredicate()).groupBy(productRoot.get("category_id"));

        TypedQuery<Category> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        return typedQuery.getResultList().stream().map(c -> new CategoryIdResult(c.getId(), c.getName())).toList();
    }
}
