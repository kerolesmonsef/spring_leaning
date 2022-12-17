package com.example.qgame.controllers;

import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.database.seeders.SeederExecuter;
import com.example.qgame.helpers.database.seeders.impls.*;
import com.example.qgame.repositories.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController

public class SeederController {

    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;
    private final BlogRepository blogRepository;
    private final ProductOptionValueRepository productOptionValueRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    public SeederController(CategoryRepository categoryRepository,
                            OptionRepository optionRepository,
                            ProductRepository productRepository,
                            BlogRepository blogRepository,
                            ProductOptionValueRepository productOptionValueRepository,
                            PaymentMethodRepository paymentMethodRepository,
                            UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.optionRepository = optionRepository;
        this.productRepository = productRepository;
        this.blogRepository = blogRepository;
        this.productOptionValueRepository = productOptionValueRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/test/seed")
    public void seed() {
        List<ISeeder> seeders = Arrays.asList(
                new CategorySeeder(categoryRepository),
                new OptionSeeder(optionRepository),
                new ProductSeeder(productRepository, categoryRepository),
                new BlogSeeder(blogRepository),
                new ProductOptionValueSeeder(productOptionValueRepository, optionRepository, productRepository),
                new PaymentMethodSeeder(paymentMethodRepository),
                new UserSeeder(userRepository)
        );


        new SeederExecuter(seeders).execute();
    }
}
