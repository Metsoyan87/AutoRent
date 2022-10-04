package com.example.autorent.controller;

import com.example.autorent.entity.Car;
import com.example.autorent.entity.User;
import com.example.autorent.repository.CarRepository;
import com.example.autorent.repository.UserRepository;
import com.example.autorent.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;

   @Autowired
    private UserRepository userRepository;

    @GetMapping("/cars/add")
    public String addCarsPage() {
        return "addCars";
    }

    @PostMapping("/cars/add")
    public String addCars(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/cars";
    }

//    @GetMapping("/cars")
//    public String carsPage(ModelMap modelMap) {
//        List<Car> carList = carRepository.findAll();;
//        modelMap.addAttribute("cars", carList);
//
//        return "cars";
//    }




    @PostMapping("/cars/changeUser")
    public String changeUser(@RequestParam("userId") int userId, @RequestParam("carId") int carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (carOptional.isPresent() && userOptional.isPresent()) {
            Car car = carOptional.get();
            User user = userOptional.get();
//            if (car.getUser() != user) {
//                car.setUser(user);
//                carRepository.save(car);
//            }
        } else if (carOptional.isPresent() && userId == 0) {
            //           carOptional.get().setUser(null);
            carRepository.save(carOptional.get());
        }
        return "redirect:/cars";
    }

}
