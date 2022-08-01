package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Category;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.CategoryRepository;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategorySeeder extends ISeeder {

    @Override
    protected List<Category> data() {

        List<Category> categories = new ArrayList<>();
        Random random = new Random();

        Faker faker = this.faker();

        for (int i = 0; i < 30; i++) {
            Category category = new Category();
            category.setName(faker.cat().name());
            category.setImage(random.nextInt(10) + ".png");
            category.setDescription(faker.lorem().paragraph(10));

            categories.add(category);
        }

        return categories;
    }

    @Override
    public void seed() {

        CategoryRepository categoryRepository = QGameApplication.getContext().getBean(CategoryRepository.class);

        categoryRepository.saveAll(this.data());
    }
}
