package com.example.qgame.helpers.converters.fromtoconverters;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.User;
import com.example.qgame.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBlogConverter implements Converter<String, Blog> {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog convert(String source) {
        return blogRepository.findById(Long.valueOf(source)).orElse(null);
    }
}
