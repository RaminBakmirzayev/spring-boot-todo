package com.example.springboottodoapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginUser {
    private String email;
    private String password;

}
