package com.example.orderservice.service;

import com.example.orderservice.dto.PaymentDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repo.OrderRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepo repo;

    @Autowired
    RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE_URL = "http://payment-ms/payments";

    public Order createOrder(Order order) {
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PLACED");
        }
        return repo.save(order);
    }

    public Order getOrderById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Mark order fulfilled AND create the payment via PaymentService
    @CircuitBreaker(name = "paymentService", fallbackMethod = "fulfillOrderFallback")
    public Order fulfillOrder(int orderId) {
        Order order = repo.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        order.setStatus("FULFILLED");
        repo.save(order);

        PaymentDto paymentRequest = new PaymentDto();
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setAmount(order.getQty() * order.getPrice());
        paymentRequest.setPaymentStatus("SUCCESS");
        paymentRequest.setTransactionId("TXN-" + UUID.randomUUID());

        PaymentDto savedPayment = restTemplate.postForObject(
                PAYMENT_SERVICE_URL,
                paymentRequest,
                PaymentDto.class
        );

        order.setPayment(savedPayment);
        return order;
    }

    // Fallback if PaymentService is down during fulfillment
    public Order fulfillOrderFallback(int orderId, Throwable t) {
        System.out.println("PaymentService unavailable during fulfillment: " + t.getMessage());
        Order order = repo.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus("FULFILLED_PAYMENT_PENDING");
            repo.save(order);
            PaymentDto stub = new PaymentDto();
            stub.setOrderId(orderId);
            stub.setPaymentStatus("PAYMENT_SERVICE_UNAVAILABLE");
            order.setPayment(stub);
        }
        return order;
    }

    // Fetch an order with its payment info
    @CircuitBreaker(name = "paymentService", fallbackMethod = "getOrderWithPaymentFallback")
    public Order getOrderWithPayment(int orderId) {
        Order order = repo.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        PaymentDto payment = restTemplate.getForObject(
                PAYMENT_SERVICE_URL + "/order/" + orderId,
                PaymentDto.class
        );
        order.setPayment(payment);
        return order;
    }

    // Fallback if PaymentService is down during lookup
    public Order getOrderWithPaymentFallback(int orderId, Throwable t) {
        System.out.println("PaymentService unavailable during lookup: " + t.getMessage());
        Order order = repo.findById(orderId).orElse(null);
        if (order != null) {
            PaymentDto stub = new PaymentDto();
            stub.setOrderId(orderId);
            stub.setPaymentStatus("PAYMENT_SERVICE_UNAVAILABLE");
            order.setPayment(stub);
        }
        return order;
    }
}