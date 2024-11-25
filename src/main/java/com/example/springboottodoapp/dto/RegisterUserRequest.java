package com.example.springboottodoapp.dto;

import com.example.springboottodoapp.enums.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RegisterUserRequest {
    private String name;
    private String surname ;
    private String email;
    private String password;

}
