package com.example.qgame.controllers.user;

import com.example.qgame.Models.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @GetMapping("/category/{slug}/{category}")
    public ModelAndView show(@PathVariable("category") Category category) {

        return new ModelAndView("products/products.html")
                .addObject("category", category);
    }
}
