package com.company;

import com.company.service.BusinessClass2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.company.service.BusinessClass;

@SpringBootApplication
public class SpringBootAopApplication implements CommandLineRunner {

    @Autowired
    BusinessClass service;

    @Autowired
    BusinessClass2 service1;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.add();
        System.out.println("---------------------------");
        service.delete();
        System.out.println("---------------------------");
        service1.view();
    }
}