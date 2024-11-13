package com.example.springboottodoapp.mapper;

import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUser(RegisterUserRequest registerUserRequest);

}
