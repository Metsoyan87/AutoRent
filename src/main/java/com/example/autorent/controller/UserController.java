package com.example.autorent.controller;

import com.example.autorent.dto.EditUserDto;
import com.example.autorent.entity.User;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;


    @GetMapping("/user")
    public String userHome() {
        return "user";
    }

    @GetMapping("/user/verify")
    public String verifyUser(@RequestParam("email") String email,
                             @RequestParam("token") String token) throws Exception {
        userService.verifyUser(email, token);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String users(@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        ModelMap modelMap,
                        @AuthenticationPrincipal CurrentUser currentUser) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> byUserRole = userService.findByUserRole(currentUser.getUser(),
                PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("users", byUserRole);

        int totalPages = byUserRole.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("userImage") MultipartFile file,
                          ModelMap modelMap) throws IOException, MessagingException {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "addUser";
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
                return "addUser";
            }
        }
        userService.uploadImageUsers(user, file);
        return "redirect:/";
    }

    @GetMapping(value = "/users/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);
    }

    @GetMapping("/users/delete{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam("userId") int id, ModelMap modelMap) {
        modelMap.addAttribute("userId", id);
        return "editUsers";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id,
                           @ModelAttribute EditUserDto dto,
                           ModelMap modelMap) {
        try {
            userService.editUser(id, dto);
            return "redirect:/users";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessage", ex.getMessage());
            return "users";
        }
    }
}
