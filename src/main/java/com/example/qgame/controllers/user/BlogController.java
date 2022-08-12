package com.example.qgame.controllers.user;

import com.example.qgame.Models.Blog;
import com.example.qgame.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Blog> blogs = blogRepository.findAll();

        return new ModelAndView("blogs/blogs")
                .addObject("blogs", blogs);
    }

    @GetMapping("/{slug}/{id}")
    @ResponseBody
    public ModelAndView show(@PathVariable("id") Long id) {


        Blog blog = blogRepository.findById(id).orElseThrow();

        return new ModelAndView("blogs/blog-details")
                .addObject("blog",blog);
    }



}
