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

        List<Map<String, Object>> options = new ArrayList<>() {{
            add(getCategoriesId());
        }};

        options.addAll(getOptionValues());
        options.add(getPriceFromTo());

        return options;
    }

    //--------------------------------------------------------
    private Map<String, Object> getCategoriesId() {

        List<CategoryIdResult> values;

        if (filterQueryBuilderResult.getFilterOptionCollection().hasType("category")) {
            values = QGameApplication.getContext().getBean(CategoryRepository.class).getIdAndName();
        } else {
            values = getCategoriesFilterIds();
        }


        return Map.ofEntries(
                Map.entry("name", "category"),
                Map.entry("type", "option"),
                Map.entry("options", values)
        );
    }

    private List<Map<String, Object>> getOptionValues() {
        CriteriaQuery<Tuple> productCriteriaQuery = cb.createQuery(Tuple.class);
        Root<Product> p = productCriteriaQuery.from(Product.class);
        p.alias("pProduct");
        Root<ProductOptionValue> pov = productCriteriaQuery.from(ProductOptionValue.class);
        Root<Option> o = productCriteriaQuery.from(Option.class);

        productCriteriaQuery.multiselect(o.get("title").alias("title"), pov.get("value").alias("value"))
                .where(
                        cb.and(
                                cb.and(filterQueryBuilderResult.getPredicate(), cb.equal(p.get("id"), pov.get("product").get("id")))
                                ,
                                cb.equal(o.get("id"), pov.get("option").get("id"))
                        )
                ).groupBy(o.get("title"), pov.get("value")); // group by here

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        List<OptionValueDTO> optionValueDTOS = typedQuery.getResultList().stream().map(t -> new OptionValueDTO(t.get("title").toString(), t.get("value").toString())).toList();


        List<Map<String, Object>> result = new ArrayList<>();

        optionValueDTOS.forEach(ov -> {
            Map<String, Object> map = result.stream().filter(r ->
                    {
                        String getName = r.get("name").toString();
                        String optionName = ov.getOptionName();
                        boolean rr = Objects.equals(getName, optionName);

                        return rr;
                    }
            ).findFirst().orElse(null);

            if (map == null) {
                map = new HashMap<>();
                map.put("name", ov.getOptionName());
                map.put("type", "option");
                map.put("options", new ArrayList<Map<String, String>>());

                result.add(map);
            }
            ((List<Map<String, String>>) map.get("options")).add(Map.ofEntries(
                    Map.entry("name", ov.getValue()),
                    Map.entry("id", ov.getValue())
            ));
        });

        return result;
    }

    private List<CategoryIdResult> getCategoriesFilterIds() {
        CriteriaQuery<Product> productCriteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productRoot.alias("pProduct");
        productRoot.join("category");

        productCriteriaQuery.select(productRoot.get("category"))
                .where(filterQueryBuilderResult.getPredicate()).groupBy(productRoot.get("category").get("id"));

        TypedQuery<Category> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        return typedQuery.getResultList().stream().map(c -> new CategoryIdResult(c.getId(), c.getName())).toList();
    }

    private Map<String, Object> getPriceFromTo() {
        return Map.ofEntries(
                Map.entry("type", "price"),
                Map.entry("name", "price"),
                Map.entry("from", 100),
                Map.entry("to", 800)
        );
    }
}
