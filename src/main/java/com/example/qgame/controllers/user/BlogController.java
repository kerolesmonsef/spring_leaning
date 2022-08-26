package com.example.qgame.controllers.user;

import com.example.qgame.Models.Blog;
import com.example.qgame.requests.BlogCommentRequest;
import com.example.qgame.repositories.BlogCommentRepository;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.services.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogCommentService blogCommentService;

    @Autowired
    private BlogCommentRepository blogCommentRepository;


    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping("")
    public ModelAndView index() {

        List<Blog> blogs = blogRepository.findAll();

        return new ModelAndView("blogs/blogs")
                .addObject("blogs", blogs);
    }

    @GetMapping("/{slug}/{id}")
    @ResponseBody
    public ModelAndView show(@PathVariable("id") Blog blog, Model model) {

        ModelAndView modelAndView = new ModelAndView("blogs/blog-details");

        if (!model.containsAttribute("blog_comment")) {
            modelAndView.addObject("blog_comment", new BlogCommentRequest());
        }

        return modelAndView
                .addObject("blog", blog)
                .addObject("comments", blogCommentRepository.getAllByBlog(blog));
    }


    @PostMapping("/{id}/comment")
    public String postComment(@PathVariable("id") Blog blog, @Valid BlogCommentRequest blogCommentRequest, BindingResult binding, RedirectAttributes attributes, Model model) {
        String backUrl = servletRequest.getHeader("Referer");

        if (binding.hasErrors()) {
            attributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.blog_comment", binding)
                    .addFlashAttribute("blog_comment", blogCommentRequest);
            return "redirect:" + backUrl;
        }

        model.addAttribute("success", "comment added successfully");

        blogCommentService.save(blogCommentRequest, blog);

        return "redirect:" + backUrl;
    }
}
