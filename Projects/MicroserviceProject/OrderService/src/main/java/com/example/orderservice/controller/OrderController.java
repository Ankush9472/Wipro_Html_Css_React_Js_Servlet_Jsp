package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repo.OrderRepo;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    OrderRepo repo;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") int id) {
        return service.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @PutMapping("/{id}/fulfill")
    public Order fulfillOrder(@PathVariable("id") int id) {
        return service.fulfillOrder(id);
    }

    @GetMapping("/{id}/with-payment")
    public Order getOrderWithPayment(@PathVariable("id") int id) {
        return service.getOrderWithPayment(id);
    }
}