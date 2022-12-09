package com.example.autorent.repository;

import com.example.autorent.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByEmailIgnoreCase(String email);

    int existsUserById(int id);

    Optional<User> findByEmail(String username);

    Page<User> findUsersById(int userId, Pageable pageable);

    Optional<User> findByEmailAndVerifyToken(String email, String token);


}
