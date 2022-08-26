package com.example.qgame.requests.admin;

import com.example.qgame.Models.Blog;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminCreateUpdateBlogRequest {

    @NotEmpty(message = "title must not be empty")
    private String title;

    @NotEmpty
    public String content;

    @NotEmpty
    public String createdBy;

    public Blog toBlog() {
        return new Blog()
                .setCreatedBy(createdBy)
                .setContent(content)
                .setTitle(title);
    }
}
