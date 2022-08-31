package com.example.qgame.requests.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.validations.ContactNumberConstraint;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Component
public class AdminCreateUpdateBlogRequest {

    @NotEmpty(message = "title must not be empty")
    @ContactNumberConstraint(message = "invalid shit", number = 12)
    private String title;

    @NotEmpty
    public String content;

    @NotEmpty
    public String createdBy;

    @NotNull
    private MultipartFile image;

    public Blog toBlog() {
        return new Blog()
                .setCreatedBy(createdBy)
                .setContent(content)
                .setTitle(title);
    }
}
