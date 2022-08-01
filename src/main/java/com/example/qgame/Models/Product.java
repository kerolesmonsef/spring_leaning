package com.example.qgame.Models;

import com.example.qgame.helpers.converters.IJsonConverter;
import com.example.qgame.helpers.entityembadable.FilesList;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition = "text")
    private String description;

    @Column(unique = true)
    private String slug;

    private int quantity;

    @Column(columnDefinition = "DECIMAL(4,2)")
    private float discount_percentage;

    @Convert(converter = IJsonConverter.class)
    @Column(columnDefinition = "json")
    private FilesList images;

    public FilesList getImages() {
        String base_path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        this.images.setPrefix(base_path + "/images/products/");
        return this.images;
    }
}
