package com.example.qgame.controllers.user;

import com.example.qgame.Models.Category;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@ControllerAdvice(annotations = Controller.class)
public class GeneralDataController {
    private final CategoryRepository categoryRepository;
    private int count = 0;

    public GeneralDataController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("allCategories")
    public List<Category> allCategories() {
        System.out.println("called -----" + (++count));
        return categoryRepository.testAll();
    }
}
