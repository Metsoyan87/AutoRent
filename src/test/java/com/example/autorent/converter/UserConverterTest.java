package com.example.autorent.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.autorent.dto.CreateUserDto;
import com.example.autorent.dto.UserResponseDto;
import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserConverterTest {
    /**
     * Method under test: {@link UserConverter#convertDtoToEntity(CreateUserDto)}
     */
    @Test
    void testConvertDtoToEntity() {
        User actualConvertDtoToEntityResult = UserConverter
                .convertDtoToEntity(new CreateUserDto("Name", "Doe", "jane.doe@example.org", "iloveyou", "4105551212",
                        "Driver Licence", "https://example.org/example", "Cart", Role.USER, "ABC123"));
        assertEquals("Doe", actualConvertDtoToEntityResult.getSurname());
        assertEquals("Name", actualConvertDtoToEntityResult.getName());
        assertEquals("jane.doe@example.org", actualConvertDtoToEntityResult.getEmail());
    }

    /**
     * Method under test: {@link UserConverter#convertDtoToEntity(CreateUserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConvertDtoToEntity2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.autorent.dto.CreateUserDto.getName()" because "createUserDto" is null
        //       at com.example.autorent.converter.UserConverter.convertDtoToEntity(UserConverter.java:17)
        //   See https://diff.blue/R013 to resolve this issue.

        UserConverter.convertDtoToEntity(new CreateUserDto());
    }

    /**
     * Method under test: {@link UserConverter#convertEntityToResponseDto(User)}
     */
    @Test
    void testConvertEntityToResponseDto() {
        User user = new User();
        user.setCard("Cart");
        user.setDriverLicense("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");
        UserResponseDto actualConvertEntityToResponseDtoResult = UserConverter.convertEntityToResponseDto(user);
        assertEquals(1, actualConvertEntityToResponseDtoResult.getId());
        assertEquals("Doe", actualConvertEntityToResponseDtoResult.getSurname());
        assertEquals("Name", actualConvertEntityToResponseDtoResult.getName());
    }

    /**
     * Method under test: {@link UserConverter#convertEntitiesToResponseDtos(List)}
     */
    @Test
    void testConvertEntitiesToResponseDtos() {
        assertTrue(UserConverter.convertEntitiesToResponseDtos(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link UserConverter#convertEntitiesToResponseDtos(List)}
     */
    @Test
    void testConvertEntitiesToResponseDtos2() {
        User user = new User();
        user.setCard("Cart");
        user.setDriverLicense("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserResponseDto> actualConvertEntitiesToResponseDtosResult = UserConverter
                .convertEntitiesToResponseDtos(userList);
        assertEquals(1, actualConvertEntitiesToResponseDtosResult.size());
        UserResponseDto getResult = actualConvertEntitiesToResponseDtosResult.get(0);
        assertEquals(1, getResult.getId());
        assertEquals("Doe", getResult.getSurname());
        assertEquals("Name", getResult.getName());
    }
}

