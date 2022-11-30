package com.example.autorent.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.security.CurrentUser;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class MyControllerAdviceTest {
    /**
     * Method under test: {@link MyControllerAdvice#currentUser(CurrentUser)}
     */
    @Test
    void testCurrentUser() {
        assertNull((new MyControllerAdvice()).currentUser(null));
    }

    /**
     * Method under test: {@link MyControllerAdvice#currentUser(CurrentUser)}
     */
    @Test
    void testCurrentUser2() {
        MyControllerAdvice myControllerAdvice = new MyControllerAdvice();
        User user = new User(1, "Name", "Doe", "jane.doe@example.org", "4105551212", "iloveyou", "Driver Licence", "Cart",
                true, Role.USER, "https://example.org/example", "ABC123");

        assertSame(user, myControllerAdvice.currentUser(new CurrentUser(user)));
    }

    /**
     * Method under test: {@link MyControllerAdvice#currentUrl(HttpServletRequest)}
     */
    @Test
    void testCurrentUrl() {
        MyControllerAdvice myControllerAdvice = new MyControllerAdvice();
        assertEquals("", myControllerAdvice.currentUrl(new MockHttpServletRequest()));
    }

    /**
     * Method under test: {@link MyControllerAdvice#currentUrl(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCurrentUrl2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.autorent.controller.MyControllerAdvice.currentUrl(MyControllerAdvice.java:24)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyControllerAdvice()).currentUrl(null);
    }
}

