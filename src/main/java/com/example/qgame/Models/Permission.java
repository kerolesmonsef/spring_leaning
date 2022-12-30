package com.example.qgame.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Admin> admin;
}
