package com.example.qgame.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    private String title;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
