package com.example.qgame.requests.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.validations.ContactNumberConstraint;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

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

    public MultipartFile image;

    public Blog toBlog() {
        return new Blog()
                .setCreatedBy(createdBy)
                .setContent(content)
                .setTitle(title);
    }
}
