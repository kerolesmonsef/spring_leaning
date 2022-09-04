package com.example.qgame.Models;

import com.example.qgame.helpers.Helper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@EntityListeners(AuditingEntityListener.class) // current timestamp
@EqualsAndHashCode
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public String getImageUrl() {
        return Helper.base_url() + "/images/categories/" + this.image;
    }

    public String getUrl() {
        return Helper.base_url() + "/category/" + Helper.slug(this.name) + "/" + this.id;
    }
}
