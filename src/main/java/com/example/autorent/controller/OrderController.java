package com.example.autorent.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/order")
    public String userHome() {
        return "order";
    }
}
