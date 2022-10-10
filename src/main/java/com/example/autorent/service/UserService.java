package com.example.autorent.service;

import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> findByUserRole(User user, Pageable pageable) {
        return userRepository.findUsersById(user.getId(), pageable);

    }

    public void saveUser(User user, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            user.setPicUrl(fileName);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        mailService.sendEmail(user.getEmail(), "Welcome", "Hi"
                + user.getName() + "\n" + "You have successfully registered");
    }

    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
    public void User(int userId,String name) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getName() != user.getName()) {
                user.setName(name);
                userRepository.save(user);
            }
        } else if (userOptional.isPresent() && userId == 0) {
            userOptional.get().setName(null);
            userRepository.save(userOptional.get());
        }
    }

}
