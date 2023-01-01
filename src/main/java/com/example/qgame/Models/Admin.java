package com.example.qgame.Models;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "admins")
@Data
@Accessors(chain = true)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;
    private String name;
    private String email;
    private String password;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "admins_roles",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    @BatchSize(size = 10)
    private List<Role> roles = new ArrayList<>();


    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "admins_permissions",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
//    @Fetch(FetchMode.SUBSELECT) // it is used with FetchType.EAGER
//    @BatchSize(size = 10) // it is used with FetchType.LAZY
    private Collection<Permission> permissions = new ArrayList<>();


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(email).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Admin)) {
            return false;
        }
        Admin that = (Admin) obj;

        return Objects.equals(this.getName(), that.getName());
    }
}
