package com.example.autorent.controller;


import com.example.autorent.service.CarService;
import com.example.autorent.service.OrderService;
import com.example.autorent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final CarService carService;
    private final OrderService orderService;


    @GetMapping("/order")
    public String userHome() {
        return "order";
    }

}
