package com.example.qgame.controllers.user;

import com.example.qgame.Models.BlogComment;
import com.example.qgame.controllers.requests.BlogCommentRequest;
import com.example.qgame.repositories.BlogCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog_comment")
public class BlogCommentController {

    @Autowired
    private BlogCommentRepository blogCommentRepository;

    @PostMapping("")
    @ResponseBody
    public Object post(@ModelAttribute("blog_comment") BlogCommentRequest request) {

//        blogCommentRepository.save(request.toBlogComment());
        return request;
    }
}
