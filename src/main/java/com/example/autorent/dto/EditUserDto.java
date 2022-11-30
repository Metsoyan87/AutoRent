package com.example.autorent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String driverLicence;
    private String cart;
    private MultipartFile image;
}
