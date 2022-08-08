package com.example.qgame.controllers.user;

import com.example.qgame.Models.Product;
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


    @ResponseBody
    @GetMapping("/")
    public List<Product> index(Model model) {


//        model.addAttribute()
        return productRepository.getLast10(PageRequest.of(1, 10));
//        return new ModelAndView("index");
    }
}
