package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =userService.getUserByEmail(email);
    return user;
    }
}
