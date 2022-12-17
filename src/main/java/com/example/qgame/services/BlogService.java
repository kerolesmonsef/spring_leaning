package com.example.qgame.services;

import com.example.qgame.Models.Blog;
import com.example.qgame.helpers.services.files.AssetFileManager;
import com.example.qgame.helpers.services.files.FileInfo;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.requests.admin.AdminBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog update(AdminBlogRequest request, Blog blog) {

        MultipartFile image = request.getImage();
        if (image != null && !image.isEmpty()) {
            AssetFileManager fileUploader = new AssetFileManager().setFile(image).setFilePath("/images/blog/");
            FileInfo fileInfo = fileUploader.upload();
            fileUploader.remove(blog.getImage());
            blog.setImage(fileInfo.getName());
        }

        blog.setContent(request.getContent())
                .setCreatedBy(request.getCreatedBy())
                .setTitle(request.getTitle());


        blogRepository.save(blog);

        return blog;
    }

    public Blog save(AdminBlogRequest blogRequest) {

        Blog blog = blogRequest.toBlog();

        AssetFileManager fileUploader = new AssetFileManager().setFile(blogRequest.getImage()).setFilePath("/images/blog/");
        FileInfo fileInfo = fileUploader.upload();
        blog.setImage(fileInfo.getName());

        blogRepository.save(blog);

        return blog;
    }

    public void delete(Blog blog) {
        blogRepository.delete(blog);

        new AssetFileManager().setFilePath("/images/blog/").remove(blog.getImage());
    }
}
