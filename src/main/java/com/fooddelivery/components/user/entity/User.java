package com.fooddelivery.components.user.entity;


import com.fooddelivery.components.cart.entity.Cart;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;



@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    private Integer phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String password;
    @ToString.Exclude
    @OneToOne(mappedBy = "user")
    private Cart cart = new Cart();

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getValue()));
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

}
