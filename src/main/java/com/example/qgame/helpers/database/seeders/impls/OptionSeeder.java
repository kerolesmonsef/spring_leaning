package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Option;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.repositories.OptionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OptionSeeder extends ISeeder<Option> {

    private final static List<String> titles = Arrays.asList("color", "size", "weight", "height", "material");

    @Override
    protected List<Option> data() {

        List<Option> options = new ArrayList<>();

        for (String title : titles) {
            Option option = new Option();

            option.setTitle(title);
            options.add(option);
        }

        return options;
    }


    @Override
    public void seed() {
        OptionRepository categoryRepository = QGameApplication.getContext().getBean(OptionRepository.class);

        categoryRepository.saveAll(this.data());
    }
}
