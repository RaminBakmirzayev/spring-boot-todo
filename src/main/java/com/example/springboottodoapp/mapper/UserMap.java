package com.example.springboottodoapp.mapper;

import com.example.springboottodoapp.dto.RegisterUserRequest;
import com.example.springboottodoapp.dto.ResponseUser;
import com.example.springboottodoapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMap {
    UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    List<ResponseUser> mapUserListToResponseListMethod(List<User> userList);

    ResponseUser mapUserToResponse(User user);

}
