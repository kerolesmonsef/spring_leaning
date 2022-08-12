package com.example.qgame.controllers.user;

import com.example.qgame.Models.Product;
import com.example.qgame.helpers.Helper;
import com.example.qgame.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    public Object index(Model model) {
        List<Product> slider_products = productRepository.getRandomN(PageRequest.of(1, 10));
        List<Product> thirty_product = productRepository.getLastN(PageRequest.of(1, 30));
        List<List<Product>> batch_products = Helper.getBatches(thirty_product, 3);

        System.out.println(batch_products.size());

        return new ModelAndView("index")
                .addObject("slider_products", slider_products)
                .addObject("batch_products", batch_products);
    }
}
