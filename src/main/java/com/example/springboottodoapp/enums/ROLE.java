package com.example.springboottodoapp.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

public enum ROLE implements GrantedAuthority {


   ROLE_ADMIN,
    ROLE_USER,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
