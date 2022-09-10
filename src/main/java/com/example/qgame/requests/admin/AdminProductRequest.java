package com.example.qgame.requests.admin;

import com.example.qgame.Models.Category;
import com.example.qgame.Models.Product;
import com.example.qgame.helpers.dto.OptionValueDTO;
import com.example.qgame.validations.ProductOptionValueValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Getter
@Setter
public class AdminProductRequest {

    @NotEmpty(message = "title must not empty")
    private String title;

    @NotNull(message = "category must not empty")
    private Category category;

    @NotEmpty
    private String description;

    private String slug;

    @NotNull
    @Min(value = 1)
    private float buyPrice;

    @Min(value = 1)
    private float price;

    @Min(value = 0)
    @Max(value = 99)
    private float discount_percentage;

    @Min(value = 0)
    private int quantity;

    @ProductOptionValueValidation
    private List<OptionValueDTO> optionValues;

    private List<MultipartFile> images;

    public Product toProduct(){
        return new Product()
                .setPrice(price)
                .setBuyPrice(buyPrice)
                .setQuantity(quantity)
                .setSlug(slug)
                .setCategory(category)
                .setDescription(description);
    }
}
