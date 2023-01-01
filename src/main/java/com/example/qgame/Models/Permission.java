package com.example.qgame.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "permissions")
    private List<Admin> admins = new ArrayList<>();


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Permission)) {
            return false;
        }
        Permission that = (Permission) obj;

        return Objects.equals(this.getName(), that.getName());
    }
}
