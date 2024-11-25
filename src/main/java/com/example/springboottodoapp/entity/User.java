package com.example.springboottodoapp.entity;

import com.example.springboottodoapp.enums.ROLE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
@EqualsAndHashCode
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName", length = 32, nullable = false)
    private String name;
    @Column(name = "userSurname", length = 32, nullable = false)
    private String surName;
    private String password;

    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    @Column(unique = true)
    private String email;
    private boolean locked;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    ROLE role;


    @CreationTimestamp
    LocalDateTime createdTime;
    @UpdateTimestamp
    LocalDateTime updatedTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return
               Collections.singletonList(role);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



    public String getUsername() {
        return email;
    }
}
