package com.example.checkuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheckUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckUserApplication.class, args);

        System.out.println("App Started");
    }
}