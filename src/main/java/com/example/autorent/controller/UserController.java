package com.example.autorent.controller;

import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor

public class UserController {


    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${AutoRent.image.folder}")
    private String folderPath;


    @GetMapping("/user")
    public String userHome() {
        return "user";
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
                          ModelMap modelMap) throws IOException {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "addUser";
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("errorMessageFile", "Please choose only image");
                return "addUser";
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            user.setPicUrl(fileName);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping(value = "/users/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/user/change")
    public String changeUser(@RequestParam("username") String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setName(username);
            userRepository.save(user);

        }
        return "redirect:/admin";
    }
}
