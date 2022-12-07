package com.example.autorent.dto;

import com.example.autorent.entity.Role;
import lombok.Data;

@Data
public class UserFilterDto {

    private String name;
    private String surname;
    private String email;
    private Role role;


}
