package com.example.qgame.controllers.user;

import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.filters.products.FilterOptionCollection;
import com.example.qgame.helpers.filters.products.FilterQueryBuilder;
import com.example.qgame.requests.ProductFilterRequest;
import com.example.qgame.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ModelAndView index() {
        return new ModelAndView("/product/index.html");
    }

    @ResponseBody
    @GetMapping("/filter")
    public Object filter(@RequestBody @Valid ProductFilterRequest request, BindingResult result) {
        return productService.filter(request.getProperties());
    }
}
