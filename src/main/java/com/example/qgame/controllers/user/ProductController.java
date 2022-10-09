package com.example.qgame.controllers.user;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductLike;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.Response;
import com.example.qgame.repositories.ProductLikeRepository;
import com.example.qgame.requests.ProductFilterRequest;
import com.example.qgame.resources.ProductResource;
import com.example.qgame.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/shop")
    public ModelAndView index() {
        return new ModelAndView("products/products.html");
    }

    @ResponseBody
    @RequestMapping(value = "/filter", method = {RequestMethod.GET, RequestMethod.POST})
    public Object filter(@RequestBody @Valid ProductFilterRequest request, BindingResult result) {
        return productService.filter(request.getProperties());
    }


    @ResponseBody
    @GetMapping("/{product}")
    public Object show(@PathVariable("product") Optional<Product> product) {
        return new Response()
                .add("product", new ProductResource(product.orElseThrow()))
                .responseEntity();
    }

    @PostMapping("/{product}/like")
    public ResponseEntity like(@PathVariable("product") Optional<Product> product) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        productService.like(user, product.orElseThrow());

        return new Response().responseEntity();
    }

    @PostMapping("/likes")
    public ResponseEntity likes() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return productService.likes(user);
    }
}
