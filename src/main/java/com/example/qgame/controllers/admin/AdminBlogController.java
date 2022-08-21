package com.example.qgame.controllers.admin;

import com.example.qgame.helpers.annotations.AdminController;
import com.example.qgame.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {

    @Autowired
    private BlogRepository blogRepository;


    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("/admin/blogs/all_blogs")
                .addObject("blogs", blogRepository.findAll());
    }
}
