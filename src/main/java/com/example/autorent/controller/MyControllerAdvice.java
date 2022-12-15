package com.example.autorent.controller;


import com.example.autorent.entity.User;
import com.example.autorent.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return currentUser.getUser();
        }
        return null;
    }

    @ModelAttribute(name = "isAdmin")
    public boolean isAdmin(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser == null ? false : currentUser.getUser().getRole().name().equals("ADMIN");
    }

    @ModelAttribute(name = "currentUrl")
    public String currentUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
