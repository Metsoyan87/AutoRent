package com.example.autorent.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.security.CurrentUser;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

class AdminControllerTest {
    /**
     * Method under test: {@link AdminController#users(Optional, Optional, ModelMap, CurrentUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUsers() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.autorent.controller.AdminController.users(AdminController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        AdminController adminController = new AdminController();
        Optional<Integer> page = Optional.of(42);
        Optional<Integer> size = Optional.of(42);
        ModelMap modelMap = new ModelMap();
        adminController.users(page, size, modelMap, new CurrentUser(new User(1, "Name", "Doe", "jane.doe@example.org",
                "4105551212", "iloveyou", "Driver Licence", "Cart", true, Role.USER, "https://example.org/example", "ABC123")));
    }

    /**
     * Method under test: {@link AdminController#users(Optional, Optional, ModelMap, CurrentUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUsers2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.autorent.controller.AdminController.users(AdminController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        AdminController adminController = new AdminController();
        Optional<Integer> page = Optional.of(42);
        Optional<Integer> size = Optional.of(42);
        ModelMap modelMap = new ModelMap();
        User user = mock(User.class);
        when(user.isEnable()).thenReturn(true);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        adminController.users(page, size, modelMap, new CurrentUser(user));
    }

    /**
     * Method under test: {@link AdminController#users(Optional, Optional, ModelMap, CurrentUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUsers3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at com.example.autorent.controller.AdminController.users(AdminController.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        AdminController adminController = new AdminController();
        Optional<Integer> page = Optional.of(0);
        Optional<Integer> size = Optional.of(42);
        ModelMap modelMap = new ModelMap();
        User user = mock(User.class);
        when(user.isEnable()).thenReturn(true);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        adminController.users(page, size, modelMap, new CurrentUser(user));
    }
}

