package com.example.autorent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.autorent.dto.EditUserDto;
import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.MailService;
import com.example.autorent.service.UserService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import javax.mail.MessagingException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {UserController.class, UserService.class, MailService.class})
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserController#addUser(User, MultipartFile, ModelMap)}
     */
    @Test
    void testAddUser() throws IOException, MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.of(user));
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserController userController = new UserController(
                new UserService(userRepository, passwordEncoder, new MailService(new JavaMailSenderImpl())));

        User user1 = new User();
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
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        assertEquals("addUser", userController.addUser(user1, file, new ModelMap()));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserController#addUser(User, MultipartFile, ModelMap)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser2() throws IOException, MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file 'null\1669798379067_', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        UserController userController = new UserController(
                new UserService(userRepository, passwordEncoder, new MailService(new JavaMailSenderImpl())));

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
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        userController.addUser(user, file, new ModelMap());
    }

    /**
     * Method under test: {@link UserController#addUser(User, MultipartFile, ModelMap)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser3() throws IOException, MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.autorent.controller.UserController.addUser(UserController.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        UserController userController = new UserController(null);

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
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        userController.addUser(user, file, new ModelMap());
    }

    /**
     * Method under test: {@link UserController#addUser(User, MultipartFile, ModelMap)}
     */
    @Test
    void testAddUser4() throws IOException, MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserService userService = mock(UserService.class);
        when(userService.findByEmail((String) any())).thenReturn(Optional.of(user));
        doNothing().when(userService).saveImageUsers((User) any(), (MultipartFile) any());
        UserController userController = new UserController(userService);

        User user1 = new User();
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
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        assertEquals("addUser", userController.addUser(user1, file, new ModelMap()));
        verify(userService).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserController#addUser(User, MultipartFile, ModelMap)}
     */
    @Test
    void testAddUser5() throws IOException, MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(userService).saveImageUsers((User) any(), (MultipartFile) any());
        UserController userController = new UserController(userService);

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
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        assertEquals("redirect:/", userController.addUser(user, file, new ModelMap()));
        verify(userService).findByEmail((String) any());
        verify(userService).saveImageUsers((User) any(), (MultipartFile) any());
    }

    /**
     * Method under test: {@link UserController#addUserPage()}
     */
    @Test
    void testAddUserPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/add");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("addUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("addUser"));
    }

    /**
     * Method under test: {@link UserController#addUserPage()}
     */
    @Test
    void testAddUserPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/add");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("addUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("addUser"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(int)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userRepository).deleteById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/delete{id}", 1);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(int)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(userRepository).deleteById((Integer) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/delete{id}", 1);
        getResult.accept("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    /**
     * Method under test: {@link UserController#editUser(int, EditUserDto, ModelMap)}
     */
    @Test
    void testEditUser() throws Exception {
        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/edit/{id}", 1);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("editUserDto", "errorMessage"))
                .andExpect(MockMvcResultMatchers.view().name("users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users"));
    }

    /**
     * Method under test: {@link UserController#editUser(int, EditUserDto, ModelMap)}
     */
    @Test
    void testEditUser2() throws Exception {
        when(userRepository.findById((Integer) any())).thenThrow(new IllegalStateException());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/edit/{id}", 1);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("editUserDto"))
                .andExpect(MockMvcResultMatchers.view().name("users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("users"));
    }

    /**
     * Method under test: {@link UserController#editUser(int, ModelMap)}
     */
    @Test
    void testEditUser3() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/edit");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link UserController#getImage(String)}
     */
    @Test
    void testGetImage() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/getImage");
        getResult.accept("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("fileName", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link UserController#userHome()}
     */
    @Test
    void testUserHome() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#users(Optional, Optional, ModelMap, CurrentUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUsers() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.example.autorent.security.CurrentUser]: Constructor threw exception; nested exception is java.lang.NullPointerException
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   java.lang.NullPointerException
        //       at com.example.autorent.security.CurrentUser.<init>(CurrentUser.java:12)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users", uriVariables);
        String[] values = new String[]{String.valueOf((Object) null)};
        MockHttpServletRequestBuilder requestBuilder = getResult.param("currentUser", values);
        Object[] controllers = new Object[]{userController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link UserController#verifyUser(String, String)}
     */
    @Test
    void testVerifyUser() throws Exception {
        User user = new User();
        user.setCart("Cart");
        user.setDriverLicence("Driver Licence");
        user.setEmail("jane.doe@example.org");
        user.setEnable(false);
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setPicUrl("https://example.org/example");
        user.setRole(Role.USER);
        user.setSurname("Doe");
        user.setVerifyToken("ABC123");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
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
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findByEmailAndVerifyToken((String) any(), (String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/verify")
                .param("email", "foo")
                .param("token", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
    }
}

