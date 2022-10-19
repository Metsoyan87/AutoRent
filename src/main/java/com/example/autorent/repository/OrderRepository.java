package com.example.autorent.repository;

import com.example.autorent.entity.Car;
import com.example.autorent.entity.Order;
import com.example.autorent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUser(User user);
    List<Order> findAllByCar(Car Car);
}
