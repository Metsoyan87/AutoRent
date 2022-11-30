package com.example.autorent.converter;

import com.example.autorent.dto.CreateUserDto;
import com.example.autorent.dto.UserResponseDto;
import com.example.autorent.entity.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class UserConverter {

    public User convertDtoToEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setSurname(createUserDto.getSurname());
        user.setEmail(createUserDto.getEmail());
        return user;
    }

    public UserResponseDto convertEntityToResponseDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }


    public List<UserResponseDto> convertEntitiesToResponseDtos(List<User> all) {
        List<UserResponseDto> authorResponseDtos = new ArrayList<>();
        for (User user : all) {
            authorResponseDtos.add(convertEntityToResponseDto(user));

        }
        return authorResponseDtos;
    }
}
