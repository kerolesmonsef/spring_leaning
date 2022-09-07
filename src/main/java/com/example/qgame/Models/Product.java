package com.example.qgame.Models;

import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.converters.IJsonConverter;
import com.example.qgame.helpers.entityembadable.FilesList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition = "text")
    private String description;

    @Column(unique = true)
    private String slug;

    private float buyPrice;

    private float price;

    private int quantity;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

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

    public String firstImageUrl(){
        return getImages().get(0);
    }

    public float priceAfterDiscount() {
        return this.priceAfterDiscount(false);
    }

    public float priceAfterDiscount(Boolean round) {
        float priceAfter = price - (discount_percentage / 100) * price;
        return round ? Math.round(price - (discount_percentage / 100) * price) : priceAfter;
    }

    public String getSmallTitle() {
        return Helper.limit(this.title, 10);
    }

    public String getUrl() {
        return Helper.base_url() + "/product/" + Helper.slug(this.title) + "/" + this.id;
    }
}
