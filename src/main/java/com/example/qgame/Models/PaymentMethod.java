package com.example.qgame.Models;

import com.example.qgame.helpers.enums.ActiveStatus;
import com.example.qgame.helpers.enums.YesNo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_methods")
@Data
public class PaymentMethod {
    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    private String display_name;

    private String className;

    private String logo;

    @Column(columnDefinition = "ENUM('yes','no')")
    private YesNo accept_refund;

    @Column(columnDefinition = "enum('active','inactive')")
    private ActiveStatus status;

    private String comment;

}
