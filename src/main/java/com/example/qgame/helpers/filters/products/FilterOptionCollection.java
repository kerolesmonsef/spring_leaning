package com.example.qgame.helpers.filters.products;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class FilterOptionCollection {
    @Getter
    private List<IFilterOption> filterOptions = new ArrayList<>();
    private FilterOptionMapper filterOptionMapper = new FilterOptionMapper();

    public FilterOptionCollection() {

    }

    public FilterOptionCollection(List<Map<String, Object>> properties) {
        properties.add(0, Map.ofEntries(Map.entry("name", "noop")));
        filterOptions = filterOptionMapper.getAll(properties);
    }


    public FilterOptionCollection push(Map<String, Object> property) {
        filterOptions.add(filterOptionMapper.get(property));
        return this;
    }

    public boolean hasType(String type) {
        return filterOptions.stream().anyMatch(filterOption -> filterOption.getName().equals(type));
    }

    public Stream stream() {
        return filterOptions.stream();
    }

    public void forEach(Consumer<IFilterOption> consumer) {
        filterOptions.forEach(consumer);
    }

    public boolean isEmpty() {
        return filterOptions.isEmpty();
    }

    public IFilterOption get(int i) {
        return filterOptions.get(i);
    }

    public int size() {
        return filterOptions.size();
    }
}
