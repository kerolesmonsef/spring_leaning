package com.example.qgame.services;

import com.example.qgame.Models.Category;
import com.example.qgame.helpers.services.files.AssetFileManager;
import com.example.qgame.helpers.services.files.FileInfo;
import com.example.qgame.repositories.CategoryRepository;
import com.example.qgame.requests.admin.AdminCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category update(AdminCategoryRequest request, Category category) {

        MultipartFile image = request.getImage();
        if (image != null && !image.isEmpty()) {
            AssetFileManager fileUploader = new AssetFileManager().setFile(image).setFilePath("/images/categories/");
            FileInfo fileInfo = fileUploader.upload();
            fileUploader.remove(category.getImage());
            category.setImage(fileInfo.getName());

        }

        category.
                setName(request.getName())
                .setDescription(request.getDescription());

        repository.save(category);

        return category;
    }


    public Category create(AdminCategoryRequest request) {
        Category category = request.toCategory();

        AssetFileManager fileUploader = new AssetFileManager().setFile(request.getImage()).setFilePath("/images/categories/");
        FileInfo fileInfo = fileUploader.upload();
        category.setImage(fileInfo.getName());


        repository.save(category);

        return category;
    }

    public void delete(Category category) {
        repository.delete(category);
        new AssetFileManager().setFilePath("/images/categories/").remove(category.getImage());
    }


}
