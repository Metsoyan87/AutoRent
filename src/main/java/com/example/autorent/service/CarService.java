package com.example.autorent.service;

import com.example.autorent.entity.Car;

import com.example.autorent.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

}
