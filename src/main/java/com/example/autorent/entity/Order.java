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
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Car car;
    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

}
