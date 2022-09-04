package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Category;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.qgame.helpers.Helper.appendFlashObjectIfNotExist;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ModelAndView index() {

        ModelAndView view = new ModelAndView("/admin/categories/all_categories.html");
        view.addObject("categories", categoryRepository.findAll());

        return view;
    }

    @GetMapping("/{category}/edit")
    public ModelAndView edit(@PathVariable Category category, Model model) {

        appendFlashObjectIfNotExist("categoryRequest", AdminCreateUpdateCategoryRequest.class, model);

        return new ModelAndView("/admin/categories/add_edit_category.html")
                .addObject("category", category);
    }
}
