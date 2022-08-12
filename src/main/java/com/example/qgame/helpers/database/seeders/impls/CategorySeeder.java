package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Category;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.CategoryRepository;
import com.github.javafaker.Faker;

import java.util.*;

public class CategorySeeder extends ISeeder<Category> {

//    @Override
//    protected List<Category> data() {
//
//        List<Category> categories = new ArrayList<>();
//        Random random = new Random();
//
//        Faker faker = this.faker();
//
//        for (int i = 0; i < 30; i++) {
//            Category category = new Category();
//            category.setName(faker.cat().name());
//            category.setImage(random.nextInt(7) + ".png");
//            category.setDescription(faker.lorem().paragraph(10));
//
//            categories.add(category);
//        }
//
//        return categories;
//    }

    @Override
    protected Collection<Category> data() {

        List<String> names = Arrays.asList("Flowers", "Furniture", "Bags", "Tools", "Grocery", "Camera");
        List<Category> categories = new ArrayList<>();

        Faker faker = this.faker();

        for (int i = 0; i < 6; i++) {
            Category category = new Category();
            category.setName(names.get(i));
            category.setImage((i + 1) + ".png");
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
