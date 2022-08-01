package com.example.qgame.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;

    private double price_each;

    private short discount_percentage;

    @Column(columnDefinition = "JSON")
    private String properties; // color - size  - ...

}
