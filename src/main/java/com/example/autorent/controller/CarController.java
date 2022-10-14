package com.example.autorent.controller;

import com.example.autorent.entity.Car;
import com.example.autorent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Value("${AutoRent.image.folder}")
    private String folderPath;
    @GetMapping("/car/add")
    public String addCarPage(ModelMap modelMap) {
        List<Car> cars = carRepository.findAll();
        modelMap.addAttribute("cars", cars);
        return "view/addCars";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car,
                         @RequestParam("carImage") MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newfile = new File(folderPath + File.separator + fileName);
            file.transferTo(newfile);
            car.setPicUrl(fileName);
           carRepository.save(car);
        }
        return "redirect:/cars";
    }

}
