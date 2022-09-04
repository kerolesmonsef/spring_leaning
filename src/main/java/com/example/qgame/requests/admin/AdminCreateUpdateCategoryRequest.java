package com.example.qgame.requests.admin;

import com.example.qgame.Models.Category;
import com.example.qgame.validations.FileValidation;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
@Data
public class AdminCreateUpdateCategoryRequest {

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty
    private String description;

    @FileValidation(requiredOnCreateOnly = true)
    private MultipartFile image;

    public Category toCategory() {
        return new Category()
                .setDescription(description)
                .setName(name);
    }
}
