package com.example.qgame.requests.admin;

import com.example.qgame.Models.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminProductRequest {

    @NotEmpty
    private String title;

    @NotNull
    private Category category;

    @NotEmpty
    private String description;

    @Column(unique = true)
    private String slug;

    private float buyPrice;

    @Min(value = 1)
    private float price;

    @Min(value = 0)
    private int quantity;

}
