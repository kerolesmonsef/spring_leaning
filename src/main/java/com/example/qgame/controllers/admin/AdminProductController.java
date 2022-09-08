package com.example.qgame.controllers.admin;

import com.example.qgame.helpers.PageWrapper;
import org.springframework.stereotype.Controller;
import com.example.qgame.Models.Product;
import com.example.qgame.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ModelAndView index() {
        Page<Product> products = service.getPageable();

        PageWrapper<Product> page= new PageWrapper<>(products,"/admin/products");

        return new ModelAndView("/admin/products/products.html")
                .addObject("page",page);
    }
}
