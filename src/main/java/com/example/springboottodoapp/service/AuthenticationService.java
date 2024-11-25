package com.example.springboottodoapp.service;

import com.example.springboottodoapp.dto.AuthenticationRequest;
import com.example.springboottodoapp.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);
}
