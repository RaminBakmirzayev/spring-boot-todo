package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.AuthenticationRequest;
import com.example.springboottodoapp.dto.AuthenticationResponse;
import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.service.AuthenticationService;
import com.example.springboottodoapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest loginUser) {

        AuthenticationResponse authenticationResponse = authenticationService.login(loginUser);

        return ResponseEntity.ok(authenticationResponse);
    }


    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);

    }

}
