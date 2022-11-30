package com.example.autorent.maper;

import com.example.autorent.dto.CreateUserDto;
import com.example.autorent.dto.UserDto;
import com.example.autorent.entity.User;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "role", defaultValue = "USER")
    User map(CreateUserDto createUserDto);

    UserDto map(User user);
}
