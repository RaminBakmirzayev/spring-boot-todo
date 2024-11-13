package com.example.springboottodoapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ResponseUser {
    private Long id;
    private String username;
    private String password;
    private String email;
}
