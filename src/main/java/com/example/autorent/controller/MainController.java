package com.example.autorent.controller;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import lombok.RequiredArgsConstructor;
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
public class MainController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        modelMap.addAttribute("user", "user");
        return "index";
    }
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

//    @GetMapping("/cars")
//    public String listing() {
//        return "cars";
//    }

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
    @GetMapping("/allUser")
    public String ad(@RequestParam("page") Optional<Integer> page,
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
        return "allUser";
    }
}
