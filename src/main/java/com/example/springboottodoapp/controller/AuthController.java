package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

private final UserService userService;

@PostMapping("/register")
public void registerUser( @RequestBody  RegisterUserRequest registerUserRequest){
userService.registerUser(registerUserRequest);
}

}
