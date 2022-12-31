package com.example.qgame.Models;

import com.example.qgame.helpers.dto.AdminRoleCountPermissionCountDto;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "admins")
@Data
@Accessors(chain = true)
public class Admin implements UserDetails {
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
    private Collection<Role> roles = new ArrayList<>();


    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "admins_permissions",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Collection<Permission> permissions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(name);
        return hcb.toHashCode();
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
