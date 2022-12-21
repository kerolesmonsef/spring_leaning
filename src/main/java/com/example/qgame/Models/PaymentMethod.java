package com.example.qgame.Models;

import com.example.qgame.helpers.enums.ActiveStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    @ColumnDefault("1")
    private boolean isActive;

    private String comment;


    public boolean isOnline() {
        return !Objects.equals(name, "cache");
    }
}
