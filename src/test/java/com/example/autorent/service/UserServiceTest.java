package com.example.autorent.service;

import com.example.autorent.dto.EditUserDto;
import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.exception.DuplicateResourceException;
import com.example.autorent.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class UserServiceTest {


    @MockBean
    private MailService mailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#findAllUsers()}
     */
    @Test
    void testFindAllUsers() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
    }

    /**
     * Method under test: {@link UserService#findByUserRole(User, Pageable)}
     */
    @Test
    void testFindByUserRole() {
        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        assertTrue(userService.findByUserRole(user, null).toList().isEmpty());
        assertEquals(2, userService.findAllUsers().size());


    }

    /**
     * Method under test: {@link UserService#findByUserRole(User, Pageable)}
     */


    /**
     * Method under test: {@link UserService#saveImageUsers(User, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveImageUsers() throws IOException, MessagingException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${AutoRent.image.folder}\1669798226175_', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        userService.saveImageUsers(user,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link UserService#saveImageUsers(User, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveImageUsers2() throws IOException, MessagingException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${AutoRent.image.folder}\1669798226316_', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        User user = mock(User.class);
        doNothing().when(user).setCart((String) any());
        doNothing().when(user).setDriverLicence((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setEnable(anyBoolean());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhoneNumber((String) any());
        doNothing().when(user).setPicUrl((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setSurname((String) any());
        doNothing().when(user).setVerifyToken((String) any());
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        userService.saveImageUsers(user,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link UserService#saveImageUsers(User, MultipartFile)}
     */
    @Test
    void testSaveImageUsers3() throws IOException, MessagingException {
        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        when(userRepository.save((User) any())).thenReturn(user);
        doNothing().when(mailService).sendHtmlEmail((String) any(), (String) any(), (String) any());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getEmail()).thenReturn("jane.doe@example.org");
        when(user1.getName()).thenReturn("Name");
        when(user1.getPassword()).thenReturn("iloveyou");
        when(user1.getVerifyToken()).thenReturn("ABC123");
        doNothing().when(user1).setCart((String) any());
        doNothing().when(user1).setDriverLicence((String) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setEnable(anyBoolean());
        doNothing().when(user1).setId(anyInt());
        doNothing().when(user1).setName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhoneNumber((String) any());
        doNothing().when(user1).setPicUrl((String) any());
        doNothing().when(user1).setRole((Role) any());
        doNothing().when(user1).setSurname((String) any());
        doNothing().when(user1).setVerifyToken((String) any());
        user1.setCart("Cart");
        user1.setDriverLicence("Driver Licence");
        user1.setEmail("jane.doe@example.org");
        user1.setEnable(true);
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setPicUrl("https://example.org/example");
        user1.setRole(Role.USER);
        user1.setSurname("Doe");
        user1.setVerifyToken("ABC123");
        userService.saveImageUsers(user1, new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));
        verify(userRepository).save((User) any());
        verify(mailService).sendHtmlEmail((String) any(), (String) any(), (String) any());
        verify(passwordEncoder).encode((CharSequence) any());
        verify(user1, atLeast(1)).getEmail();
        verify(user1).getName();
        verify(user1).getPassword();
        verify(user1).getVerifyToken();
        verify(user1).setCart((String) any());
        verify(user1).setDriverLicence((String) any());
        verify(user1).setEmail((String) any());
        verify(user1, atLeast(1)).setEnable(anyBoolean());
        verify(user1).setId(anyInt());
        verify(user1).setName((String) any());
        verify(user1, atLeast(1)).setPassword((String) any());
        verify(user1).setPhoneNumber((String) any());
        verify(user1).setPicUrl((String) any());
        verify(user1, atLeast(1)).setRole((Role) any());
        verify(user1).setSurname((String) any());
        verify(user1, atLeast(1)).setVerifyToken((String) any());
    }

    /**
     * Method under test: {@link UserService#saveImageUsers(User, MultipartFile)}
     */
    @Test
    void testSaveImageUsers4() throws IOException, MessagingException {
        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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
        when(userRepository.save((User) any())).thenReturn(user);
        doNothing().when(mailService).sendHtmlEmail((String) any(), (String) any(), (String) any());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getEmail()).thenThrow(new IllegalStateException());
        when(user1.getName()).thenThrow(new IllegalStateException());
        when(user1.getPassword()).thenThrow(new IllegalStateException());
        when(user1.getVerifyToken()).thenThrow(new IllegalStateException());
        doNothing().when(user1).setCart((String) any());
        doNothing().when(user1).setDriverLicence((String) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setEnable(anyBoolean());
        doNothing().when(user1).setId(anyInt());
        doNothing().when(user1).setName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhoneNumber((String) any());
        doNothing().when(user1).setPicUrl((String) any());
        doNothing().when(user1).setRole((Role) any());
        doNothing().when(user1).setSurname((String) any());
        doNothing().when(user1).setVerifyToken((String) any());
        user1.setCart("Cart");
        user1.setDriverLicence("Driver Licence");
        user1.setEmail("jane.doe@example.org");
        user1.setEnable(true);
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setPicUrl("https://example.org/example");
        user1.setRole(Role.USER);
        user1.setSurname("Doe");
        user1.setVerifyToken("ABC123");
        assertThrows(IllegalStateException.class, () -> userService.saveImageUsers(user1,
                new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{}))));
        verify(user1).getPassword();
        verify(user1).setCart((String) any());
        verify(user1).setDriverLicence((String) any());
        verify(user1).setEmail((String) any());
        verify(user1).setEnable(anyBoolean());
        verify(user1).setId(anyInt());
        verify(user1).setName((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhoneNumber((String) any());
        verify(user1).setPicUrl((String) any());
        verify(user1).setRole((Role) any());
        verify(user1).setSurname((String) any());
        verify(user1).setVerifyToken((String) any());
    }

    /**
     * Method under test: {@link UserService#getUserImage(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.io.FileNotFoundException: C:\Users\metso\IdeaProjects\AutoRent\images\foo.txt (РќРµ СѓРґР°РµС‚СЃСЏ РЅР°Р№С‚Рё СѓРєР°Р·Р°РЅРЅС‹Р№ С„Р°Р№Р»)
        //       at java.io.FileInputStream.open0(Native Method)
        //       at java.io.FileInputStream.open(FileInputStream.java:219)
        //       at java.io.FileInputStream.<init>(FileInputStream.java:157)
        //       at java.io.FileInputStream.<init>(FileInputStream.java:112)
        //       at com.example.autorent.service.UserService.getUserImage(UserService.java:77)
        //   See https://diff.blue/R013 to resolve this issue.


        userService.getUserImage("foo.txt");
    }



    /**
     * Method under test: {@link UserService#save(User)}
     */
    @Test
    void testSave() throws DuplicateResourceException {

        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
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

        userService.save(user);
        ArgumentCaptor<User> productArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository)
                .save(productArgumentCaptor.capture());

        User captorProduct = productArgumentCaptor.getValue();
        assertThat(captorProduct).isEqualTo(user);
    }

    /**
     * Method under test: {@link UserService#verifyUser(String, String)}
     */
    @Test
    void testVerifyUser() throws Exception {
        assertThrows(Exception.class, () -> userService.verifyUser("jane.doe@example.org", "ABC123"));
        assertThrows(Exception.class, () -> userService.verifyUser("l.UlUlUlUlUl.U", "ABC123"));
        assertThrows(Exception.class, () -> userService.verifyUser("Email", "ABC123"));
    }

    /**
     * Method under test: {@link UserService#deleteById(int)}
     */
    @Test
    void testDeleteById() throws DuplicateResourceException {
        User user = new User();
        user.setId(1);
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
        user.setEmail("Email");
        user.setEnable(true);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");

        userService.save(user);
        userService.deleteById(user.getId());
        Optional<User> byId = userRepository.findById(user.getId());
        assertFalse(byId.isPresent());


    }

    /**
     * Method under test: {@link UserService#deleteById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.dao.EmptyResultDataAccessException: No class com.example.autorent.entity.User entity with id 123 exists!
        //       at java.util.Optional.orElseThrow(Optional.java:408)
        //       at com.sun.proxy.$Proxy142.deleteById(null)
        //       at com.example.autorent.service.UserService.deleteById(UserService.java:118)
        //   See https://diff.blue/R013 to resolve this issue.

        userRepository.deleteById(1);
    }

    /**
     * Method under test: {@link UserService#editUser(int, EditUserDto)}
     */
    @Test
    void testEditUser() {
        assertThrows(IllegalStateException.class, () -> userService.editUser(1, new EditUserDto()));
    }
}

