package com.example.autorent.repository;


import com.example.autorent.entity.Car;

import com.example.autorent.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

   List<Car> findByModel(String carname);

   Page<Car> findCarsById(int carId, Pageable pageable);
}
