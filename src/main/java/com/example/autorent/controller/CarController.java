package com.example.autorent.controller;

import com.example.autorent.dto.EditCarDto;
import com.example.autorent.entity.Car;
import com.example.autorent.entity.User;
import com.example.autorent.repository.CarRepository;
import com.example.autorent.security.CurrentUser;
import com.example.autorent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @GetMapping("/car/add")
    public String addCarPage(ModelMap modelMap) {
        List<Car> cars = carService.findAll();
        modelMap.addAttribute("cars", cars);
        return "addCars";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car,
                         @RequestParam("carImage") MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            //  List<Car> byModel = carService.findByModel(car.getModel());
            carService.saveCar(car, file);
        }
        return "redirect:/cars";

    }

    @GetMapping("/cars")
    public String cars(@RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size,
                       ModelMap modelMap
    ) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Page<Car> byCarName = carService.findByCarName(
                PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("cars", byCarName);

        int totalPages = byCarName.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "cars";
    }

    @GetMapping(value = "/car/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return carService.getCarImage(fileName);
    }

    @GetMapping("/car/change")
    public String editCarPage(@RequestParam("carId") int id, ModelMap modelMap) {
        Optional<Car> carOptional = carService.findById(id);
        if (carOptional.isEmpty()) {
            return "redirect:/admin";
        }
        modelMap.addAttribute("carId", id);
        return "editCars";
    }

    @PostMapping("/car/change/{id}")
    public String editCar(@PathVariable int id,
                          @ModelAttribute EditCarDto dto,
                          ModelMap modelMap) {
        try {
            carService.editCar(id, dto);
            return "redirect:/cars";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessage", ex.getMessage());
        }
        return "cars";
    }

    @GetMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carService.deleteById(id);
        return "redirect:/cars";
    }

}
