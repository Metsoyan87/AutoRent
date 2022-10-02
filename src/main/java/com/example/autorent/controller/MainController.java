package com.example.autorent.controller;

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
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if(error != null && error.equals("true")){
            modelMap.addAttribute("error", "true");
        }
        return "loginPage";
    }
}
