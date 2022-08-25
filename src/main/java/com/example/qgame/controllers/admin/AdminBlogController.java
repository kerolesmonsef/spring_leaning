package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.helpers.annotations.AdminController;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping("/create")
    public ModelAndView create(@Valid AdminCreateUpdateBlogRequest blogRequest) {
        return this.edit(new Blog());
    }

    @GetMapping("/edit/{blog}")
    public ModelAndView edit(@PathVariable("blog") Blog blog) {
        return new ModelAndView("/admin/blogs/add_edit_blog")
                .addObject("blog", blog)
                .addObject("blogRequest", new AdminCreateUpdateBlogRequest());
    }

    @PostMapping()
    public String store() {
        return "/";
    }


    public String update(@PathVariable("blog") Blog blog,@Valid AdminCreateUpdateBlogRequest blogRequest){
        return "redirect:";
    }

}
