package com.example.qgame.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog_comments")
@Data
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    @CreatedDate
    private Date createdAt;
}
