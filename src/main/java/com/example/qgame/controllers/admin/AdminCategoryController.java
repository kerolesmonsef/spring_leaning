package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Category;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.requests.admin.AdminCategoryRequest;
import com.example.qgame.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.example.qgame.helpers.Helper.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController extends IController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public ModelAndView index() {

        ModelAndView view = new ModelAndView("admin/categories/all_categories.html");
        view.addObject("categories", categoryRepository.findAll());

        return view;
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        return this.edit(new Category(), model);
    }

    @GetMapping("/{category}/edit")
    public ModelAndView edit(@PathVariable Category category, Model model) {

        if (!model.containsAttribute("categoryRequest")) {
            model.addAttribute("categoryRequest", applicationContext.getBean(AdminCategoryRequest.class));
        }

        return new ModelAndView("admin/categories/add_edit_category.html")
                .addObject("category", category);
    }

    @PutMapping("/{category}")
    public ModelAndView update(@PathVariable Category category, @Valid AdminCategoryRequest request, BindingResult bindings, RedirectAttributes attributes) {

        if (bindings.hasErrors()) {
            appendFlashAttribute("categoryRequest", request, attributes, bindings);
            return back();
        }

        categoryService.update(request, category);

        attributes.addFlashAttribute("alertSuccess", "Blog Updated Successfully");

        return back();
    }

    @PostMapping("/")
    public ModelAndView store(@Valid AdminCategoryRequest request, BindingResult bindings, RedirectAttributes attributes) {

        if (bindings.hasErrors()) {
            appendFlashAttribute("categoryRequest", request, attributes, bindings);
            return back();
        }

        categoryService.create(request);

        attributes.addFlashAttribute("alertSuccess", "Category Added Successfully");

        return back();
    }


    @DeleteMapping("/{category}")
    public ModelAndView destroy(@PathVariable Category category) {

        categoryService.delete(category);

        return back();
    }
}
