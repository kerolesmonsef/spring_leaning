package com.example.qgame.requests.admin;

import com.example.qgame.Models.Blog;
import com.example.qgame.validations.FileValidation;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Component
public class AdminBlogRequest {

    @NotEmpty(message = "title must not be empty")
    private String title;

    @NotEmpty
    public String content;

    @NotEmpty
    public String createdBy;

    @FileValidation(requiredOnCreateOnly = true)
    private MultipartFile image;

    public Blog toBlog() {
        return new Blog()
                .setCreatedBy(createdBy)
                .setContent(content)
                .setTitle(title);
    }

}
