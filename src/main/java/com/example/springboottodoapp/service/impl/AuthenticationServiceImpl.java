package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.Security.JWT.JWTUtil;
import com.example.springboottodoapp.dto.AuthenticationRequest;
import com.example.springboottodoapp.dto.AuthenticationResponse;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.AuthenticationService;
import com.example.springboottodoapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        User user = userService.getUserByEmail(request.getEmail());

        String token = jwtUtil.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token, jwtUtil.getJwtExpiration());

        return authenticationResponse;
    }
}
