package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.config.PasswordEncoderConfig;
import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.dto.ResponseUser;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.mapper.UserMapper;
import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper=UserMapper.INSTANCE;

    private final PasswordEncoderConfig passwordEncoder;
    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        log.info("Registering user: {}", registerUserRequest);
        registerUserRequest.setPassword(passwordEncoder.encoder().encode(registerUserRequest.getPassword()));
        // TODO: Implement user registration logic here
        User user= userMapper.mapToUser(registerUserRequest);
        userRepository.save(user);
        log.info("User registered successfully: {}", user);
    }

    @Override
    public List<ResponseUser> getAllUsers() {
        return null;
    }

    @Override
    public ResponseUser getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
  return    userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found by email"+email));

    }

    @Override
    public void updateUser(Long id, RegisterUserRequest registerUserRequest) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
