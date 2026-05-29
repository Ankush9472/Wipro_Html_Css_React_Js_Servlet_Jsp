package com.example.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.orderservice.dto.PaymentDto;

@Entity
@Table(name = "ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private int id;
    private String name;
    private int qty;
    private double price;

    private String status;

    @Transient
    private PaymentDto payment;
}