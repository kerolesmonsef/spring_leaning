package com.example.qgame.services;

import com.example.qgame.Models.Option;
import com.example.qgame.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OptionService {
    @Autowired
    OptionRepository repository;

    public List<Option> findOrCreate(List<String> titles) {
        final List<Option> options = repository.getByListOfTitle(titles);

        List<String> notFoundTitles = titles.stream().filter((title_i) -> {
            return options.stream().noneMatch((option -> {
                return option.getTitle().equals(title_i);
            }));
        }).toList();

        List<Option> newOptions = new ArrayList<>();
        notFoundTitles.stream().forEach((title) -> {
            newOptions.add(new Option().setTitle(title));
        });

        if (!newOptions.isEmpty()) {
            repository.saveAll(newOptions);
            options.addAll(newOptions);
        }

        return options;
    }
}
