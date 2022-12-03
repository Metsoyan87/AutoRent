package com.example.autorent.service;

import com.example.autorent.dto.EditUserDto;
import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.exception.DuplicateResourceException;
import com.example.autorent.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Value("${AutoRent.image.folder}")
    private String folderPath;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


//    public Optional<User> findByEmail(String theEmail) {
//
//        Optional<User> result = userRepository.findByEmail(theEmail);
//        User theUser = null;
//        if(result.isPresent()) {
//            theUser = result.get();
//        }
//        else {
//           throw new RuntimeException("Did not find userId: " + theUser);
//        }
//        return theUser;
//    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> findByUserRole(@NotNull User user, Pageable pageable) {
        return userRepository.findUsersById(user.getId(), pageable);

    }

    public void saveImageUsers(User user, @NotNull MultipartFile file) throws IOException, MessagingException {

        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            user.setPicUrl(fileName);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setEnable(false);
        user.setVerifyToken(UUID.randomUUID().toString());
        userRepository.save(user);

        mailService.sendHtmlEmail(user.getEmail(), "Please verify your email",
                "Hi " + user.getName() + "\n" +
                        "Please verify your account by clicking on this link " +
                        "<a href=\"http://localhost:8080/user/verify?email=" +
                        user.getEmail() + "&token=" +
                        user.getVerifyToken() + "\">Activate</a>");


    }

    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }
    public void save(User user) throws DuplicateResourceException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already exists");
        }
        userRepository.save(user);
    }

    public void verifyUser(String email, String token) throws Exception {
        Optional<User> userOptional = userRepository.findByEmailAndVerifyToken(email, token);
        if (userOptional.isEmpty()) {
            throw new Exception("user Does not exists with email and token");
        }
        User user = userOptional.get();
        if (user.isEnable()) {
            throw new Exception("User already enabled");
        }
        user.setEnable(true);
        user.setVerifyToken(null);
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void editUser(int id, EditUserDto dto) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        User user = optional.get();
        edit(user, dto);
    }

    private void edit(User user, @NotNull EditUserDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String phoneNumber = dto.getPhoneNumber();
        String cart = dto.getCart();
        String driverLicence = dto.getDriverLicence();
        MultipartFile image = dto.getImage();


        if (StringUtils.hasText(email)) {
            user.setEmail(email);
        }
        if (StringUtils.hasText(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (StringUtils.hasText(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }
        if (StringUtils.hasText(cart)) {
            user.setCart(cart);
        }
        if (StringUtils.hasText(driverLicence)) {
            user.setDriverLicence(driverLicence);
        }
        if (!image.isEmpty() && image.getSize() > 0) {
            if (image.getContentType() != null && !image.getContentType().contains("image")) {
                throw new IllegalStateException("Please provide right image");
            }
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            try {
                image.transferTo(newFile);
            } catch (IOException e) {
                throw new IllegalStateException("Something went wrong, please try again");
            }
            user.setPicUrl(fileName);
            user.setEnable(true);
        }
        userRepository.save(user);
    }
}
