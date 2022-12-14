package com.example.autorent.dto;


import com.example.autorent.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Name is empty")
    @Size(min = 3, max = 10, message = "Check your name")
    private String name;

    @NotBlank(message = "Surname is empty")
    @Size(min = 3, max = 10, message = "Check your surname")
    private String surname;

    @Email
    @NotBlank(message = "email is empty")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "email address is empty")
    private String email;

    @NotBlank(message = "You forgot to create a password")
    @Size(min = 8, message = "password should be minimum 8 characters")
    private String password;

    @NotBlank(message = "fill the phone number field")
    private String phoneNumber;
    private String driverLicence;
    private String picUrl;
    private String cart;

    @Enumerated(EnumType.STRING)
    private Role role ;
    private String verifyToken;



}
