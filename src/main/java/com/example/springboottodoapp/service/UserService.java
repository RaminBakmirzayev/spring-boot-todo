package com.example.springboottodoapp.service;

import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.dto.ResponseUser;
import com.example.springboottodoapp.entity.User;

import java.util.List;

public interface  UserService {

    void registerUser(RegisterUserRequest registerUserRequest);
    List<ResponseUser> getAllUsers();
    ResponseUser getUserById(Long id);
    User getUserByEmail(String email);
     User updateUser(Long id, RegisterUserRequest registerUserRequest);
    void deleteUser(Long id);

    public default Long getCurrentUserId() {
        return null;
    }
}
