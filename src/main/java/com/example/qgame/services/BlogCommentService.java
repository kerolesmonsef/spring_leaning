package com.example.qgame.services;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.BlogComment;
import com.example.qgame.requests.BlogCommentRequest;
import com.example.qgame.repositories.BlogCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogCommentService {

    @Autowired
    private BlogCommentRepository blogCommentRepository;

    public BlogComment save(BlogCommentRequest blogCommentRequest, Blog blog) {

        BlogComment blogComment = blogCommentRequest
                .toBlogComment()
                .setBlog(blog);

        return blogCommentRepository.save(blogComment);
    }
}
