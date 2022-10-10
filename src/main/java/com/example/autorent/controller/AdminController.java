package com.example.autorent.controller;

import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    private final UserService userService;
//
//
//    @GetMapping("/admin")
//    public String getUsers(ModelMap modelMap) {
//        List<User> all = userRepository.findAll();
//        modelMap.addAttribute("users", all);
//        return "admin";
//    }

    @GetMapping("/admin")
    public String users(@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        ModelMap modelMap,
                        @AuthenticationPrincipal CurrentUser currentUser) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> byUserRole = userRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("users", byUserRole);

        int totalPages = byUserRole.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin";
    }
}
