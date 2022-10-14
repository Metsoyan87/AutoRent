package com.example.autorent.repository;


import com.example.autorent.entity.Car;

import com.example.autorent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {

    Optional<Car> findByEmail(String carname);
}
