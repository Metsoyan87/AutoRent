package com.example.autorent.controller;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        modelMap.addAttribute("user", "user");
        return "index";
    }
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/cars")
    public String listing() {
        return "cars";
    }

    @GetMapping("/addUser")
    public String userAdd() {
        return "addUser";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin";
            } else if (user.getRole() == Role.USER) {
                return "redirect:/users";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if (error != null && error.equals("true")) {
            modelMap.addAttribute("error", "true");
        }
        return "loginPage";
    }
}
