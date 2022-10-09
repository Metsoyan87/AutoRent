package com.example.autorent.controller.view;

import com.example.autorent.entity.Car;
import com.example.autorent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/car/add")
    public String addTaskPage(ModelMap modelMap) {
        List<Car> cars = carRepository.findAll();
        modelMap.addAttribute("cars", cars);
        return "view/addCars";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car) {
       carRepository.save(car);
        return "redirect:/cars";
    }

}
