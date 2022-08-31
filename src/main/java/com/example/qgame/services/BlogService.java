package com.example.qgame.services;

import com.example.qgame.Models.Blog;
import com.example.qgame.helpers.services.files.AssetFileUploader;
import com.example.qgame.helpers.services.files.FileInfo;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog update(AdminCreateUpdateBlogRequest request, Blog blog) {

        MultipartFile image = request.getImage();
        if (image != null && !image.isEmpty()) {
            try {
                AssetFileUploader fileUploader = new AssetFileUploader().setFile(image).setFilePath("/images/blog/");
                FileInfo fileInfo = fileUploader.upload();
                fileUploader.remove(blog.getImage());
                blog.setImage(fileInfo.getName());
            } catch (IOException e) {

            }
        }

        blog.setContent(request.getContent());
        blog.setCreatedBy(request.getCreatedBy());
        blog.setTitle(request.getTitle());


        blogRepository.save(blog);

        return blog;
    }

    public Blog save(AdminCreateUpdateBlogRequest blogRequest) {

        Blog blog = blogRequest.toBlog();

        try {
            AssetFileUploader fileUploader = new AssetFileUploader().setFile(blogRequest.getImage()).setFilePath("/images/blog/");
            FileInfo fileInfo = fileUploader.upload();
            blog.setImage(fileInfo.getName());
        } catch (IOException e) {

        }

        blogRepository.save(blog);

        return blog;
    }
}
