package com.example.qgame.Models;

import javax.persistence.*;

@Entity
@Table(name = "user_addresses")
public class UserAddress {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String address;
    private String street;
    private String home_number;
    private String floor_number;
    private String region;
    private double latitude;
    private double longitude;
}
