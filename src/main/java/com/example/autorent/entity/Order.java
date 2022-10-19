package com.example.autorent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date carStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date carEndDate;
    @ManyToOne
    private Car car;
    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

}
