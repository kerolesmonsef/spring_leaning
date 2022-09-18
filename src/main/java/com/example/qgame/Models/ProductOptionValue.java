package com.example.qgame.Models;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class ProductOptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    private String value;

//    @Column(updatable = false,insertable = false)
//    private Long option_id;
}
