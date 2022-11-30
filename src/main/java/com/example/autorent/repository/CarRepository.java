package com.example.autorent.repository;


import com.example.autorent.entity.Car;

import com.example.autorent.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
//    List<Order> findAllByCar(Car car);
}
