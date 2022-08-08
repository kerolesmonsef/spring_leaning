package com.example.qgame.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@EntityListeners(AuditingEntityListener.class) // current stimestapm
@EqualsAndHashCode
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

//    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
