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

        List<CategoryIdResult> categoriesIds;

        if (filterQueryBuilderResult.getFilterOptionCollection().hasType("category")) {
            categoriesIds = QGameApplication.getContext().getBean(CategoryRepository.class).getIdAndName();
        } else {
            categoriesIds = getCategoriesFilterIds();
        }


        return Map.ofEntries(
                Map.entry("name", "category"),
                Map.entry("type", "category"),
                Map.entry("categoriesIds", categoriesIds)
        );
    }

    private List<Map<String, Object>> getOptionValues() {
        CriteriaQuery<Tuple> productCriteriaQuery = cb.createQuery(Tuple.class);
        Root<Product> p = productCriteriaQuery.from(Product.class);
        Root<ProductOptionValue> pov = productCriteriaQuery.from(ProductOptionValue.class);
        Root<Option> o = productCriteriaQuery.from(Option.class);

        productCriteriaQuery.multiselect(p.get("id").alias("idd"), o.get("title").alias("title"), pov.get("value").alias("value"))
                .where(
                        cb.and(
                                cb.and(filterQueryBuilderResult.getPredicate(), cb.equal(p.get("id"), pov.get("product").get("id")))
                                ,
                                cb.equal(o.get("id"), pov.get("option").get("id"))
                        )
                ).groupBy(o.get("title"), pov.get("value"), p.get("id")); // group by here

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(productCriteriaQuery).unwrap(Query.class);

        List<OptionValueDTO> optionValueDTOS = typedQuery.getResultList().stream().map(t -> new OptionValueDTO(t.get("title").toString(), t.get("value").toString())).toList();

        List<OptionValuesDto> optionListValues = new ArrayList<>();

        optionValueDTOS.forEach((ov) -> {

            OptionValuesDto existsOptionValuesDto = optionListValues.stream().filter(ovs -> ovs.getOptionName().equals(ov.getOptionName())).findFirst().orElse(null);

            if (existsOptionValuesDto != null) {
                existsOptionValuesDto.getValues().add(ov.getValue());
            } else {
                optionListValues.add(new OptionValuesDto(ov.getOptionName(), new ArrayList<String>(Collections.singletonList(ov.getValue()))));
            }
        });


        return optionListValues.stream().map(ovs -> Map.ofEntries(
                Map.entry("type", "option"),
                Map.entry("name", ovs.getOptionName()),
                Map.entry("values", ovs.getValues())
        )).toList();
    }

    private List<CategoryIdResult> getCategoriesFilterIds() {
        CriteriaQuery<Product> productCriteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
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
