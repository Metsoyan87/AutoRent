package com.example.autorent.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootTest
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class LocaleConfigTest {
    @Autowired
    private LocaleConfig localeConfig;

    /**
     * Method under test: {@link LocaleConfig#localeResolver()}
     */
    @Test
    void testLocaleResolver() {
        assertTrue(localeConfig.localeResolver() instanceof SessionLocaleResolver);
    }

    /**
     * Method under test: {@link LocaleConfig#localeChangeInterceptor()}
     */
    @Test
    void testLocaleChangeInterceptor() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        LocaleChangeInterceptor actualLocaleChangeInterceptorResult = (new LocaleConfig()).localeChangeInterceptor();
        assertFalse(actualLocaleChangeInterceptorResult.isIgnoreInvalidLocale());
        assertEquals("lang", actualLocaleChangeInterceptorResult.getParamName());
    }

    /**
     * Method under test: {@link LocaleConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    void testAddInterceptors() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     InterceptorRegistry.registrations

        localeConfig.addInterceptors(new InterceptorRegistry());
    }

    /**
     * Method under test: {@link LocaleConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddInterceptors2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.web.servlet.config.annotation.InterceptorRegistry.addInterceptor(org.springframework.web.servlet.HandlerInterceptor)" because "registry" is null
        //       at com.example.autorent.config.LocaleConfig.addInterceptors(LocaleConfig.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        localeConfig.addInterceptors(null);
    }

    /**
     * Method under test: {@link LocaleConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    void testAddInterceptors3() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        when(interceptorRegistry.addInterceptor((HandlerInterceptor) any()))
                .thenReturn(new InterceptorRegistration(new UserRoleAuthorizationInterceptor()));
        localeConfig.addInterceptors(interceptorRegistry);
        verify(interceptorRegistry).addInterceptor((HandlerInterceptor) any());
    }
}

