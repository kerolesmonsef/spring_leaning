package com.example.qgame.Models;

import com.example.qgame.helpers.enums.ActiveStatus;
import com.example.qgame.helpers.enums.YesNo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "payment_methods")
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    private String display_name;

    private String className;

    private String logo;


//    @Column(columnDefinition = "enum('active','inactive')")
//    private ActiveStatus status;

    private String comment;


    public boolean isOnline(){
        return !Objects.equals(name, "cache");
    }
}
