package com.example.qgame.controllers;

import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.database.seeders.SeederExecuter;
import com.example.qgame.helpers.database.seeders.impls.PermissionSeeder;
import com.example.qgame.helpers.database.seeders.impls.RoleSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeederController {


    private final RoleSeeder roleSeeder;
    private final PermissionSeeder permissionSeeder;


//    private final CategoryRepository categoryRepository;
//    private final OptionRepository optionRepository;
//    private final ProductRepository productRepository;
//    private final BlogRepository blogRepository;
//    private final ProductOptionValueRepository productOptionValueRepository;
//    private final PaymentMethodRepository paymentMethodRepository;
//    private final UserRepository userRepository;
//    private final RoleServices roleServices;
//    private final PermissionService permissionService;
//
//    public SeederController(CategoryRepository categoryRepository,
//                            OptionRepository optionRepository,
//                            ProductRepository productRepository,
//                            BlogRepository blogRepository,
//                            ProductOptionValueRepository productOptionValueRepository,
//                            PaymentMethodRepository paymentMethodRepository,
//                            UserRepository userRepository, RoleServices roleServices, PermissionService permissionService) {
//        this.categoryRepository = categoryRepository;
//        this.optionRepository = optionRepository;
//        this.productRepository = productRepository;
//        this.blogRepository = blogRepository;
//        this.productOptionValueRepository = productOptionValueRepository;
//        this.paymentMethodRepository = paymentMethodRepository;
//        this.userRepository = userRepository;
//        this.roleServices = roleServices;
//        this.permissionService = permissionService;
//    }

    @ResponseBody
    @GetMapping("/test/seed")
    public void seed() {
        List<ISeeder> seeders = Arrays.asList(
//                new CategorySeeder(categoryRepository),
//                new OptionSeeder(optionRepository),
//                new ProductSeeder(productRepository, categoryRepository),
//                new BlogSeeder(blogRepository),
//                new ProductOptionValueSeeder(productOptionValueRepository, optionRepository, productRepository),
//                new PaymentMethodSeeder(paymentMethodRepository),
//                new UserSeeder(userRepository),
                roleSeeder,
                permissionSeeder
                );


        new SeederExecuter(seeders).execute();
    }
}
