package com.example.springboottodoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private long expiresIn;
}
