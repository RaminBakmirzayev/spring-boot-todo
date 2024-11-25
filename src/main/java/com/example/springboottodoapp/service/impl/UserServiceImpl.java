package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.Security.config.PasswordEncoderConfig;
import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.dto.ResponseUser;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.enums.ROLE;
import com.example.springboottodoapp.exceptions.DuplicateResourceException;
import com.example.springboottodoapp.mapper.UserMap;

import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMap userMapper = UserMap.INSTANCE;

    private final PasswordEncoderConfig passwordEncoder;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {

        String email = registerUserRequest.getEmail();
        if (userRepository.existsUserByEmail(email)) {
            throw new DuplicateResourceException("Email already registered");
        }
        User user = User.builder()
                .name(registerUserRequest.getName())
                .surName(registerUserRequest.getSurname())
                .email(registerUserRequest.getEmail())
                .enabled(true)
                .role(ROLE.valueOf("ROLE_USER"))
                .password(passwordEncoder.encoder().encode(registerUserRequest.getPassword())).build();

        userRepository.save(user);
        log.info("User registered successfully: {}", user);
    }

    @Override
    public List<ResponseUser> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userMapper.mapUserListToResponseListMethod(userList);

    }


    @Override
    public ResponseUser getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found by id:" + id));
        return userMapper.mapUserToResponse(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found by email" + email));

    }

    @Override
    public User updateUser(Long id, RegisterUserRequest registerUserRequest) {


        if (checkUserIdIsSameWithCurrentUserId(id)) {               //Yalnız login olan user update oluna bilir
            User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found by id" + id));
            boolean updated = false;

            if (updateUserField(registerUserRequest.getName(), user.getName(), user::setName))
                updated = true;

            if (updateUserField(registerUserRequest.getSurname(), user.getSurName(), user::setSurName))
                updated = true;

            if (updateUserField(registerUserRequest.getEmail(), user.getEmail(), user::setEmail))
                updated = true;

            if (!updated)
                throw new RuntimeException("No fields to update");

            return userRepository.save(user);

        } else {
            throw new AccessDeniedException("You can only update your own account");
        }
    }

    @Override
    public void deleteUser(Long id) {
        if(checkUserIdIsSameWithCurrentUserId(id)) {               //Yalnız login olan user siline bilir
            userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found by id" + id));
            userRepository.deleteById(id);
            log.info("User deleted successfully");
        }else {
            throw new AccessDeniedException("You can only delete your own account");
        }
    }


    private boolean updateUserField(String newValue, String currentValue, Consumer<String> updater) {
        if (newValue != null && !newValue.equals(currentValue)) {
            updater.accept(newValue);
            return true;
        }
        return false;
    }

    @Override
    public Long getCurrentUserId() {                    //Hal hazırda login olan userin id-sini return edir

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User currentUser = userRepository.findByEmail(email).orElse(null);
            if (currentUser != null) {
                return currentUser.getId();
            }
        }
        return null;
    }

   private boolean checkUserIdIsSameWithCurrentUserId(Long id) {
        if (getCurrentUserId().equals(id)) return true;
        return false;
    }


}
