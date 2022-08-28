package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.helpers.services.AssetFileUploader;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static com.example.qgame.helpers.Helper.appendFlashAttribute;
import static com.example.qgame.helpers.Helper.appendFlashObjectIfNotExist;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("/admin/blogs/all_blogs")
                .addObject("blogs", blogRepository.findAll());
    }


    @GetMapping("/{blog}")
    public ModelAndView show(@PathVariable("blog") Blog blog) {
        return new ModelAndView("redirect:/admin/blogs/" + blog.getId() + "/edit");
    }

    @GetMapping("/create")
    public ModelAndView create(Model model) {

        return this.edit(new Blog(), model);
    }


    @GetMapping("/{blog}/edit")
    public ModelAndView edit(@PathVariable("blog") Blog blog, Model model) {

        ModelAndView modelAndView = new ModelAndView("/admin/blogs/add_edit_blog");

        appendFlashObjectIfNotExist("blogRequest", AdminCreateUpdateBlogRequest.class, model);

        return modelAndView
                .addObject("blog", blog);
    }

    @PostMapping()
    public String store() {
        return "/";
    }


    @PutMapping("/{blog}")
    public ModelAndView update(@PathVariable("blog") Blog blog, @Valid AdminCreateUpdateBlogRequest blogRequest, BindingResult binding, RedirectAttributes attributes, Model model, @RequestParam("image") MultipartFile file) throws IOException {

        new AssetFileUploader().setFile(file).upload();

        String backUrl = servletRequest.getHeader("Referer");
        ModelAndView modelAndView = new ModelAndView("redirect:" + backUrl);


        if (binding.hasErrors()) {
            appendFlashAttribute("blogRequest", blogRequest, attributes, binding);

            return modelAndView;
        }

        model.addAttribute("alertSuccess", "Blog Added Successfully");

        return modelAndView;
    }

}
