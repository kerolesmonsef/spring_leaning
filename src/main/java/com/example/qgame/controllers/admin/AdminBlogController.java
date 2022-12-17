package com.example.qgame.controllers.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminBlogRequest;
import com.example.qgame.services.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;

import static com.example.qgame.helpers.Helper.appendFlashAttribute;
import static com.example.qgame.helpers.Helper.redirectBack;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController extends IController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("admin/blogs/all_blogs")
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


        if (!model.containsAttribute("blogRequest")) {
            model.addAttribute("blogRequest", applicationContext.getBean(AdminBlogRequest.class));
        }

        return new ModelAndView("admin/blogs/add_edit_blog")
                .addObject("blog", blog);
    }

    @PostMapping("/")
    public ModelAndView store(@Valid AdminBlogRequest blogRequest, BindingResult binding, RedirectAttributes attributes) {
        if (binding.hasErrors()) {

            appendFlashAttribute("blogRequest", blogRequest, attributes, binding);

            return back();
        }

        blogService.save(blogRequest);

        attributes.addFlashAttribute("alertSuccess", "Blog Added Successfully");

        return back();
    }


    @PutMapping("/{blog}")
    public ModelAndView update(@PathVariable("blog") Blog blog, @Valid AdminBlogRequest blogRequest, BindingResult binding, RedirectAttributes attributes, Model model, @RequestParam("image") MultipartFile file) throws IOException {

        if (binding.hasErrors()) {

            appendFlashAttribute("blogRequest", blogRequest, attributes, binding);

            return back();
        }

        blogService.update(blogRequest, blog);

        attributes.addFlashAttribute("alertSuccess", "Blog Updated Successfully");

        return back();
    }

    @DeleteMapping("/{blog}")
    public ModelAndView destroy(@PathVariable Blog blog, RedirectAttributes attributes) {

        attributes.addFlashAttribute("alertSuccess", "Blog Deleted Successfully");

        blogService.delete(blog);

        return redirectBack(servletRequest);
    }

}
