package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.dto.ResponseUser;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.exceptions.DuplicateResourceException;
import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users")
@RequiredArgsConstructor
@RestController

public class UserControler {
    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        userService.registerUser(registerUserRequest);
        return ResponseEntity.ok().body("Successfully registered!");
    }

    @GetMapping()
    public List<ResponseUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateStudent(@PathVariable Long id, @RequestBody RegisterUserRequest userRequest) {

        User user = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> getUserById(@PathVariable Long id) {
        ResponseUser responseUser = userService.getUserById(id);
        return ResponseEntity.ok(responseUser);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("succesfully deleted");

    }
}
