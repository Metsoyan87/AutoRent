package com.example.autorent.service;

import com.example.autorent.entity.User;
import com.example.autorent.exception.DuplicateResourceException;
import com.example.autorent.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.autorent.MockData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MailService mailService;


    @Test
    void shouldSaveUser() {
        //given
        User user = getUnsavedUser();
        //when
        when(userRepository.existsByEmailIgnoreCase(user.getEmail())).thenReturn(false);
        userService.save(user);
        //then
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void shouldThrowExceptionAsDuplicateEmail() {
        //given
        User user = getUnsavedUser();
        //when
        when(userRepository.existsByEmailIgnoreCase(user.getEmail())).thenReturn(true);
        //then
        assertThrows(DuplicateResourceException.class, () -> userService.save(user));
    }

    @Test
    void shouldVerifyUser() {
        //given
        User user = getDisabledUser();
        String token = "some token";
        //when
        doReturn(Optional.of(user)).when(userRepository)
                .findByEmailAndVerifyToken(anyString(), anyString());

        userService.verifyUser(user.getEmail(), token);
        //then
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void shouldThrowExceptionAsUserDoesNotExist() {
        //given
        User user = getDisabledUser();

        //when
        when(userRepository.findByEmailAndVerifyToken(anyString(), anyString()))
                .thenReturn(Optional.empty());
        userService.save(user);

        //then
        assertThrows(RuntimeException.class, () -> userService.verifyUser(anyString(),anyString()));
    }

    @Test
    void shouldThrowExceptionAsUserIsEnabled() {
        //given
        User user = getEnabledUser();

        //when
        when(userRepository.findByEmailAndVerifyToken(anyString(), anyString()))
                .thenReturn(Optional.of(user));
        userService.save(user);

        //then
        assertThrows(RuntimeException.class, () -> userService.verifyUser(anyString(),anyString()));
    }
}


