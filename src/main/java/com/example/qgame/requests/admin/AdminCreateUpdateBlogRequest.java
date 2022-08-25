package com.example.qgame.requests.admin;

import com.example.qgame.Models.Blog;
import lombok.Data;

@Data
public class AdminCreateUpdateBlogRequest {
    private String title;

    public String content;

    public String createdBy;

    public Blog toBlog() {
        return new Blog()
                .setCreatedBy(createdBy)
                .setContent(content)
                .setTitle(title);
    }
}
