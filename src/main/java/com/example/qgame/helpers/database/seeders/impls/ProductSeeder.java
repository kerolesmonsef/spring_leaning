package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Category;
import com.example.qgame.Models.Product;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.entityembadable.FilesList;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.repositories.ProductRepository;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductSeeder extends ISeeder<Product> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected List<Product> data() {
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = new ArrayList<>();
        final int MAX_IMAGE = 27;
        Random random = new Random();

        Faker faker = this.faker();

        for (int i = 0; i < 200; i++) {
            long buy_price = faker.number().numberBetween(10, 999);
            Product product = new Product();
            product.setTitle(faker.name().title());
            product.setDescription(faker.lorem().paragraph(20));
            product.setCategory(categories.get(random.nextInt(categories.size())));
            product.setDiscountPercentage(random.nextFloat(100f));
            product.setSlug(faker.internet().slug() + "-" + random.nextInt(99999));
            product.setImages(new FilesList().add(random.nextInt(MAX_IMAGE) + ".jpg").add(random.nextInt(MAX_IMAGE) + ".jpg"));
            product.setQuantity(random.nextInt(50));
            product.setBuyPrice(buy_price);
            product.setPrice(faker.number().numberBetween(buy_price, (long) (buy_price + buy_price * .1)));

            products.add(product);
        }

        return products;
    }

    @Override
    public void seed() {

        productRepository.saveAll(this.data());
    }
}
