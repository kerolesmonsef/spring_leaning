package com.example.qgame.Models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "blog_comments")
@Data
@Accessors(chain = true)
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @NotNull
    private String email;

    private String name;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    @CreatedDate
    private Date createdAt = new Date();

}
