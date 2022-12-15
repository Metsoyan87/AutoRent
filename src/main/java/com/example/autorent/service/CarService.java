package com.example.autorent.service;

import com.example.autorent.dto.EditCarDto;
import com.example.autorent.entity.Car;
import com.example.autorent.entity.City;
import com.example.autorent.entity.StatusType;
import com.example.autorent.repository.CarRepository;
import com.example.autorent.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CarService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    @Value("${AutoRent.image.folder}")
    private String folderPath;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByModel(String model) {
        return carRepository.findByModel(model);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public Page<Car> findByCarName(Pageable pageable) {
        return carRepository.findAll(pageable);

    }

    public void saveCar(Car car, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newfile = new File(folderPath + File.separator + fileName);
            file.transferTo(newfile);
            car.setPicUrl(fileName);
            carRepository.save(car);
        }
    }

    public byte[] getCarImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    public void editCar(int id, EditCarDto dto) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
       Car car = optional.get();
        edit(car, dto);
    }

    private void edit(Car car, @NotNull EditCarDto dto) {
        String description = dto.getDescription();
        double price = dto.getPrice();
        City city = dto.getCity();
        StatusType statusType = dto.getStatusType();
        MultipartFile image = dto.getImage();


        if (StringUtils.hasText(description)) {
            car.setDescription(description);
        }
        if (StringUtils.hasText(String.valueOf(price))) {
            car.setPrice(price);
        }
        if (StringUtils.hasText(String.valueOf(city))) {
            car.setCity(city);
        }
        if (StringUtils.hasText(String.valueOf(statusType))) {
            car.setStatusType(statusType);
        }
        if (!image.isEmpty() && image.getSize() > 0) {
            if (image.getContentType() != null && !image.getContentType().contains("image")) {
                throw new IllegalStateException("Please provide right image");
            }
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            try {
                image.transferTo(newFile);
            } catch (IOException e) {
                throw new IllegalStateException("Something went wrong, please try again");
            }
            car.setPicUrl(fileName);
//            car.setEnable(true);
        }
        carRepository.save(car);
    }


}
