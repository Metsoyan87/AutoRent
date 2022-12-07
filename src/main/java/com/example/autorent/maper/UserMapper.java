package com.example.autorent.maper;

import com.example.autorent.dto.CreateUserDto;
import com.example.autorent.dto.UserDto;
import com.example.autorent.entity.User;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User map(CreateUserDto createUserDto);

    UserDto map(User user);
}
