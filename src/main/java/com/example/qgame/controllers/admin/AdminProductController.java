package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Category;
import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.paginations.IPageWrapper;
import com.example.qgame.helpers.paginations.Pagination;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.requests.admin.AdminProductRequest;
import org.springframework.stereotype.Controller;
import com.example.qgame.Models.Product;
import com.example.qgame.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.qgame.helpers.Helper.appendToModelIfNotExist;
import static com.example.qgame.helpers.Helper.redirectBack;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HttpServletRequest servletRequest;


    @GetMapping()
    public ModelAndView index() {

        IPageWrapper<Product> page = service.getPageable();

        return new ModelAndView("/admin/products/products.html")
                .addObject("page", page);
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        return this.edit(new Product(), model);

    }

    @GetMapping("/{product}/edit")
    public ModelAndView edit(@PathVariable Product product, Model model) {

        appendToModelIfNotExist("productRequest", AdminProductRequest.class, model);

        return new ModelAndView("/admin/products/add_edit_product")
                .addObject("product", product)
                .addObject("categories", categoryRepository.findAll());
    }

    @PostMapping("/")
    @ResponseBody
    public ModelAndView store(@Valid AdminProductRequest request, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {

            Helper.appendFlashAttribute("productRequest", request, attributes, result);

            return redirectBack(servletRequest);
        }

        service.store(request);

        attributes.addFlashAttribute("alertSuccess", "Product Added Successfully");

        return redirectBack(servletRequest);
    }

}
