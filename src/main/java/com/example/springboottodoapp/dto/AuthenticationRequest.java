package com.example.springboottodoapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

}
