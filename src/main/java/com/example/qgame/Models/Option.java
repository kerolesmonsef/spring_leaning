package com.example.qgame.Models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "options")
@Accessors(chain = true)
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @Column(unique = true)
    private String title;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
