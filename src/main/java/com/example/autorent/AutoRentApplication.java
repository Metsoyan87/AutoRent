package com.example.autorent;

import com.example.autorent.entity.Role;
import com.example.autorent.entity.User;
import com.example.autorent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@SpringBootApplication
@EnableAsync
public class AutoRentApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AutoRentApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

    }

//    @Override
//    public void run(String... args) throws Exception {
//        Optional<User> byEmail = userRepository.findByEmail("admin@mail.com");
//
//        if (byEmail.isEmpty()) {
//            userRepository.save(User.builder()
//                    .name("admin")
//                    .surname("admin")
//                    .email("admin@mail.com")
//                    .phoneNumber("00000000")
//                    .cart("00000000")
//                    .isEnable(true)
//                    .driverLicence("00000000")
//                    .password(passwordEncoder().encode("admin"))
//                    .role(Role.ADMIN)
//                    .build());
//        }
//    }
}
