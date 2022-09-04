package com.example.qgame.services;

import com.example.qgame.Models.Blog;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog update(AdminCreateUpdateBlogRequest request,Blog blog){


        return blog;
    }
}
