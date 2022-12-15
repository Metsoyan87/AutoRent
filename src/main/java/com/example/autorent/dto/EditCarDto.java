package com.example.autorent.dto;


import com.example.autorent.entity.City;
import com.example.autorent.entity.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCarDto {

    private String description;
    private double price;
    private City city;
    private StatusType statusType;
    private MultipartFile image;
}
