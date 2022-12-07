package com.example.autorent;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;

public class MockData {

    public static User getUnsavedUser(){
        return User.builder()
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .phoneNumber("4105551212")
                .password("iloveyou")
                .driverLicense("Driver Licence")
                .card("Cart")
                .isEnable(true)
                .role(Role.USER)
                .picUrl("https://example.org/example")
                .verifyToken("ABC123")
                .build();
    }

    public static User getDisabledUser(){
        return User.builder()
                .id(1)
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .phoneNumber("4105551212")
                .password("iloveyou")
                .driverLicense("Driver Licence")
                .card("Cart")
                .isEnable(false)
                .role(Role.USER)
                .picUrl("https://example.org/example")
                .verifyToken("ABC123")
                .build();
    }

    public static User getEnabledUser(){
        return User.builder()
                .id(1)
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .phoneNumber("4105551212")
                .password("iloveyou")
                .driverLicense("Driver Licence")
                .card("Cart")
                .isEnable(true)
                .role(Role.USER)
                .picUrl("https://example.org/example")
                .verifyToken("ABC123")
                .build();
    }
}
