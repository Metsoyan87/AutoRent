package com.example.autorent.repository;

import com.example.autorent.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String username);

    Page<User> findUsersById(int userId, Pageable pageable);

    Optional<User> findByEmailAndVerifyToken(String email,String token);

}
