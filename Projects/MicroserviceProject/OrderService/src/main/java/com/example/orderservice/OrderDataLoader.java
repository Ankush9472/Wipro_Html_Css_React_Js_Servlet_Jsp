package com.example.orderservice;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repo.OrderRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDataLoader {

    @Autowired
    OrderRepo repo;

    @PostConstruct
    public void loadDummyData() {
        if (repo.count() > 0) {
            System.out.println("Orders already present, skipping data load");
            return;
        }

        Order o1 = new Order(1, "Laptop",  1, 55000.00, "PLACED", null);
        Order o2 = new Order(2, "Phone",   2, 20000.00, "PLACED", null);
        Order o3 = new Order(3, "Headset", 3,  1500.00, "PLACED", null);
        Order o4 = new Order(4, "Monitor", 1, 12000.00, "PLACED", null);

        repo.save(o1);
        repo.save(o2);
        repo.save(o3);
        repo.save(o4);

        System.out.println("Order dummy data loaded safely");
    }
}