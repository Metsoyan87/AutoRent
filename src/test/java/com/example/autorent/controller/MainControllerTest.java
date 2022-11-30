package com.example.autorent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.MailService;
import com.example.autorent.service.UserService;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

@ContextConfiguration(classes = {MainController.class, UserService.class, MailService.class})
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class MainControllerTest {
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MainController mainController;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link MainController#aboutPage()}
     */
    @Test
    void testAboutPage() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(mainController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link MainController#loginSuccess(CurrentUser)}
     */
    @Test
    void testLoginSuccess() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
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

        UserRepository userRepository = mock(UserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        assertEquals("redirect:/",
                (new MainController(
                        new UserService(userRepository, passwordEncoder, new MailService(new JavaMailSenderImpl()))))
                        .loginSuccess(null));
    }

    /**
     * Method under test: {@link MainController#loginSuccess(CurrentUser)}
     */
    @Test
    void testLoginSuccess2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
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

        UserRepository userRepository = mock(UserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        MainController mainController = new MainController(
                new UserService(userRepository, passwordEncoder, new MailService(new JavaMailSenderImpl())));
        assertEquals("redirect:/users",
                mainController.loginSuccess(new CurrentUser(new User(1, "Name", "Doe", "jane.doe@example.org", "4105551212",
                        "iloveyou", "Driver Licence", "Cart", true, Role.USER, "https://example.org/example", "ABC123"))));
    }

    /**
     * Method under test: {@link MainController#loginSuccess(CurrentUser)}
     */
    @Test
    void testLoginSuccess3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
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

        UserRepository userRepository = mock(UserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        MainController mainController = new MainController(
                new UserService(userRepository, passwordEncoder, new MailService(new JavaMailSenderImpl())));
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.ADMIN);
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
        CurrentUser currentUser = mock(CurrentUser.class);
        when(currentUser.getUser()).thenReturn(user);
        assertEquals("redirect:/admin", mainController.loginSuccess(currentUser));
        verify(currentUser).getUser();
        verify(user).getRole();
        verify(user).setCart((String) any());
        verify(user).setDriverLicence((String) any());
        verify(user).setEmail((String) any());
        verify(user).setEnable(anyBoolean());
        verify(user).setId(anyInt());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhoneNumber((String) any());
        verify(user).setPicUrl((String) any());
        verify(user).setRole((Role) any());
        verify(user).setSurname((String) any());
        verify(user).setVerifyToken((String) any());
    }

    /**
     * Method under test: {@link MainController#mainPage(ModelMap)}
     */
    @Test
    void testMainPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    /**
     * Method under test: {@link MainController#mainPage(ModelMap)}
     */
    @Test
    void testMainPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    /**
     * Method under test: {@link MainController#userPage(Optional, Optional, ModelMap, CurrentUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUserPage() throws Exception {
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
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/userpage", uriVariables);
        String[] values = new String[]{String.valueOf((Object) null)};
        MockHttpServletRequestBuilder requestBuilder = getResult.param("currentUser", values);
        Object[] controllers = new Object[]{mainController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}

