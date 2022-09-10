package com.example.qgame.helpers.converters.fromtoconverters;

import com.example.qgame.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.example.qgame.Models.Category;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {

    @Autowired
    CategoryRepository repository;

    @Override
    public Category convert(String source) {
        return repository.findById(Long.valueOf(source)).orElseThrow();
    }
}
