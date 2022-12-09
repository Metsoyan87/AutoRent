package com.example.autorent.service;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.exception.DuplicateResourceException;
import com.example.autorent.exception.EntityNotFoundException;
import com.example.autorent.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.example.autorent.MockData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private MultipartFile multipartFile;
    @Mock
    private Page page;

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
        doReturn(Optional.of(user)).when(userRepository).findByEmailAndVerifyToken(anyString(), anyString());

        userService.verifyUser(user.getEmail(), token);
        //then
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void shouldThrowExceptionAsUserDoesNotExist() {
        //given
        User user = getDisabledUser();
        //when
        when(userRepository.findByEmailAndVerifyToken(anyString(), anyString())).thenReturn(Optional.empty());
        userService.save(user);
        //then
        assertThrows(RuntimeException.class, () -> userService.verifyUser(anyString(), anyString()));
    }

    @Test
    void shouldThrowExceptionAsUserIsEnabled() {
        //given
        User user = getEnabledUser();

        //when
        when(userRepository.findByEmailAndVerifyToken(anyString(), anyString())).thenReturn(Optional.of(user));
        userService.save(user);

        //then
        assertThrows(RuntimeException.class, () -> userService.verifyUser(anyString(), anyString()));
    }

    @Test
    void shouldAllUsers() {
        //given
        List<User> user = new LinkedList<>();
        //when
        when(userRepository.findAll()).thenReturn(user);
        userService.findAllUsers();
        //then
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldFindByEmail() {

        //given
        Optional<User> user = Optional.of(new User());
        //when
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        userService.findByEmail(getSavedUser().getEmail());
        //then
        verify(userRepository, times(1)).findByEmail(getSavedUser().getEmail());
    }


    @Test
    void testSaveImageUsers2() throws IOException, MessagingException {
        User user = getSavedUser();
        when(userRepository.save(any())).thenReturn(user);
        doNothing().when(mailService).sendHtmlEmail(any(), any(), any());
        when(passwordEncoder.encode(any())).thenReturn("secret");

        User user1 = getSavedUser();
        userService.uploadImageUsers(user1, new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));
        verify(userRepository).save(any());
        verify(mailService).sendHtmlEmail(any(), any(), any());
        verify(passwordEncoder).encode(any());
        assertFalse(user1.isEnable());
        assertEquals(Role.USER, user1.getRole());
        assertEquals("secret", user1.getPassword());
    }

    @Test
    void uploadImageUsers() throws Exception {
        String fileName = "sampleFile.jpg";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "uploaded-file",
                fileName,
                "text/plain",
                "This is the file content".getBytes()
        );
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/api/files/upload");
        User user = getSavedUser();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.uploadImageUsers(user, sampleFile);

        user.setPicUrl(user.getPicUrl() + 1);
        verify(userRepository).saveAndFlush(user);

    }

    @Test
    void shouldReturnByUserRole() {
        //given
        User user = getSavedUser();
        //when
        when(userRepository.findUsersById(anyInt(), any())).thenReturn(page);
        userService.findByUserRole(user, page.getPageable());
        verify(userRepository, times(1)).findUsersById(getSavedUser().getId(), page.getPageable());

    }


    @Test
    void deleteById() {
        //given
        User user = getSavedUser();
        //when
        userService.deleteById(user.getId());
        //then
        Mockito.verify(userRepository).deleteById(user.getId());

    }

    @Test
    public void shouldAThrowExceptionWhenUserDoesntExist() {
        User user = getSavedUser();
        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        //then
        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(user.getId()));
    }


}


