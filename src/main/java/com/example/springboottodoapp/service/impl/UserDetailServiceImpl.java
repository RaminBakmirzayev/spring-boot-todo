package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    //private final UserService userService;
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//     return userRepository.findByUsername(username);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find by email " + email));
    }
}
