package com.example.autorent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    private String driverLicence;
    private String cart;
    private boolean isEnable;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String picUrl;
    private String verifyToken;


}
