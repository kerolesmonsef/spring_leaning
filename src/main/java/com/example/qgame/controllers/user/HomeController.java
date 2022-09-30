package com.example.qgame.controllers.user;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.Category;
import com.example.qgame.Models.Product;
import com.example.qgame.helpers.Helper;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlogRepository blogRepository;


    @GetMapping("/")
    public Object index(Model model) {
        List<Product> slider_products = productRepository.getRandomN(PageRequest.of(0, 10));
        List<Product> thirty_product = productRepository.getLastN(PageRequest.of(0, 30));
        List<List<Product>> batch_products = Helper.getBatches(thirty_product, 3);
        List<Category> some_categories = categoryRepository.RandomN(PageRequest.of(0, 6));
        List<Blog> recent_blogs = blogRepository.getRecent(PageRequest.of(0, 4));

        System.out.println(thirty_product.get(0).firstImageUrl());

        return new ModelAndView("index")
                .addObject("slider_products", slider_products)
                .addObject("batch_products", batch_products)
                .addObject("recent_blogs", recent_blogs)
                .addObject("some_categories", some_categories);
    }

}
