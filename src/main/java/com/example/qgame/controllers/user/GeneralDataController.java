package com.example.qgame.controllers.user;

import com.example.qgame.helpers.CategoryRepositoryGetter;
import com.example.qgame.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@ControllerAdvice(annotations = Controller.class)
public class GeneralDataController {
    private final CategoryRepository categoryRepository;
    private int count = 0;

    public GeneralDataController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("allCategories")
    public CategoryRepositoryGetter allCategories() {

        return new CategoryRepositoryGetter(categoryRepository);
    }
}
