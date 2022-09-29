package com.example.autorent.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String description;
    private double price;
    private Date carYear;
    private String mark;

    @Enumerated(value = EnumType.STRING)
    private City city;

    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;
}
