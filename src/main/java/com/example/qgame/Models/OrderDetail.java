package com.example.qgame.Models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_details")
@Accessors(chain = true)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;

    @Column(name = "price_each")
    private double priceEach;


    @Column(columnDefinition = "JSON")
    private String properties; // color - size  - ...


    public float priceAfterDiscount() {
        return product.priceAfterDiscount() * quantity;
    }

    public float priceBeforeDiscount() {
        return singlePrice() * quantity;
    }

    private float singlePrice() {
        return this.product.getPrice();
    }
}
