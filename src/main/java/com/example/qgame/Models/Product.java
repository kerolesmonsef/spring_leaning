package com.example.qgame.Models;

import com.example.qgame.helpers.Helper;
import com.example.qgame.helpers.converters.IJsonConverter;
import com.example.qgame.helpers.entityembadable.FilesList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@Accessors(chain = true)
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    private String title;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition = "text")
    private String description;

    private String slug;

    private float buyPrice;

    private float price;

    private int quantity;

    @Column(name = "discountPercentage",columnDefinition = "DECIMAL(4,2)")
    private float discountPercentage;

    @Convert(converter = IJsonConverter.class)
    @Column(columnDefinition = "json")
    private FilesList images;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

//    @Column(updatable = false,insertable = false)
//    private Long category_id;

    public FilesList getNativeImages() {
        return this.images;
    }

    public FilesList images() {
        if (this.images == null) {
            return new FilesList();
        }
        String base_path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        this.images.setPrefix(base_path + "/images/products/");
        return this.images;
    }

    public String firstImageUrl() {
        return images().get(0);
    }

    public float priceAfterDiscount() {
        return this.priceAfterDiscount(false);
    }

    public float priceAfterDiscount(Boolean round) {
        float priceAfter = price - (discountPercentage / 100) * price;
        return round ? Math.round(price - (discountPercentage / 100) * price) : priceAfter;
    }

    public String getSmallTitle() {
        return Helper.limit(this.title, 10);
    }

    public String getUrl() {
//        return "no-slug";
        return Helper.base_url() + "/product/" + Helper.slug(this.title) + "/" + this.id;
    }
}
