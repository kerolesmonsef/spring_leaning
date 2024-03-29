package com.example.qgame.helpers.filters.products;

import com.example.qgame.helpers.filters.products.filterOptionsImpls.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FilterOptionMapper {


    private ApplicationContext applicationContext;

    @Autowired
    public FilterOptionMapper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static Map<String, Class<? extends IFilterOption>> filterOptionsTypes = Map.ofEntries(
            Map.entry("category", CategoryFilterOption.class),
            Map.entry("priceAfterDiscount", PriceFilterOption.class),
            Map.entry("keyword", KeywordFilterOption.class),
            Map.entry("option", OptionValueFilterOption.class),
            Map.entry("noop", NoopFilterOption.class)
    );


    public IFilterOption get(Map<String, Object> property) {

        String name = (String) property.get("name");

        if (filterOptionsTypes.containsKey(name)) {
            NoopFilterOption noopFilterOption = this.applicationContext.getBean(NoopFilterOption.class);
            return this.applicationContext.getBean(filterOptionsTypes.get(name)).init(property);
        }

        return new NoopFilterOption();
    }

    public List<IFilterOption> getAll(List<Map<String, Object>> properties) {
        return properties.stream().map(this::get).toList();
    }
}
