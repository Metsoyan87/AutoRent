package com.example.autorent.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import org.junit.jupiter.api.Test;

class CurrentUserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CurrentUser#CurrentUser(User)}
     *   <li>{@link CurrentUser#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
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
        assertSame(user, (new CurrentUser(user)).getUser());
    }

    /**
     * Method under test: {@link CurrentUser#CurrentUser(User)}
     */
    @Test
    void testConstructor2() {
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
        CurrentUser actualCurrentUser = new CurrentUser(user);
        assertEquals(1, actualCurrentUser.getAuthorities().size());
        assertTrue(actualCurrentUser.isEnabled());
        assertTrue(actualCurrentUser.isCredentialsNonExpired());
        assertTrue(actualCurrentUser.isAccountNonLocked());
        assertTrue(actualCurrentUser.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualCurrentUser.getUsername());
        assertSame(user, actualCurrentUser.getUser());
        assertEquals("iloveyou", actualCurrentUser.getPassword());
    }

    /**
     * Method under test: {@link CurrentUser#CurrentUser(User)}
     */
    @Test
    void testConstructor3() {
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
        CurrentUser actualCurrentUser = new CurrentUser(user);
        assertEquals(1, actualCurrentUser.getAuthorities().size());
        assertFalse(actualCurrentUser.isEnabled());
        assertTrue(actualCurrentUser.isCredentialsNonExpired());
        assertTrue(actualCurrentUser.isAccountNonLocked());
        assertTrue(actualCurrentUser.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualCurrentUser.getUsername());
        assertSame(user, actualCurrentUser.getUser());
        assertEquals("iloveyou", actualCurrentUser.getPassword());
    }
}

