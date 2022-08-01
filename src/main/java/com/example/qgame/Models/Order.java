package com.example.qgame.Models;

import com.example.qgame.helpers.enums.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private UserAddress address;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> detail;

    private double products_price;

    private double products_price_after_discount;

    private double total_price;

    @ColumnDefault("0")
    private double taxes;

    private OrderStatus status;

    private Date delivered_at;

    @Column(columnDefinition = "TEXT")
    private String note;

    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}
