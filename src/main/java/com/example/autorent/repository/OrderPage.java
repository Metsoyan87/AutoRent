package com.example.autorent.repository;

import com.example.autorent.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPage extends JpaRepository<Order,Integer> {

}
