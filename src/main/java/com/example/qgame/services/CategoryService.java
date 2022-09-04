package com.example.qgame.services;

import com.example.qgame.Models.Category;
import com.example.qgame.helpers.services.files.AssetFileUploader;
import com.example.qgame.helpers.services.files.FileInfo;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.requests.admin.AdminCreateUpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category update(AdminCreateUpdateCategoryRequest request, Category category) {

        MultipartFile image = request.getImage();
        if (image != null && !image.isEmpty()) {
            try {
                AssetFileUploader fileUploader = new AssetFileUploader().setFile(image).setFilePath("/images/categories/");
                FileInfo fileInfo = fileUploader.upload();
                fileUploader.remove(category.getImage());
                category.setImage(fileInfo.getName());
            } catch (IOException e) {

            }
        }

        category.
                setName(request.getName())
                .setDescription(request.getDescription());

        repository.save(category);

        return category;
    }


    public Category create(AdminCreateUpdateCategoryRequest request) {
        Category category = request.toCategory();

        try {
            AssetFileUploader fileUploader = new AssetFileUploader().setFile(request.getImage()).setFilePath("/images/categories/");
            FileInfo fileInfo = fileUploader.upload();
            category.setImage(fileInfo.getName());
        } catch (IOException e) {

        }

        repository.save(category);

        return category;
    }

    public void delete(Category category) {
        repository.delete(category);
        new AssetFileUploader().setFilePath("/images/categories/").remove(category.getImage());
    }
}
