package com.example.qgame.Models;

import com.example.qgame.helpers.enums.ActiveStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_methods")
@Data
@Accessors(chain = true)
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String display_name;

    private String className;

    private String logo;

//    @Column(columnDefinition = "enum('active','inactive')")
//    private ActiveStatus status;

    private String comment;


    public boolean isOnline() {
        return !Objects.equals(name, "cache");
    }
}
