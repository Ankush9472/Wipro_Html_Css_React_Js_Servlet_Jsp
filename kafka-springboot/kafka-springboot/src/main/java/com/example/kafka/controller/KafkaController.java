package com.example.kafka.controller;

import com.example.kafka.consumer.KafkaConsumerService;
import com.example.kafka.producer.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    public KafkaController(KafkaProducerService producerService,
                           KafkaConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producerService.sendMessage(message);
        return "Message sent: " + message;
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return consumerService.getReceivedMessages();
    }

    @GetMapping("/health")
    public String health() {
        return "Kafka Spring Boot app is running!";
    }

    @GetMapping("/send-loop")
    public String sendLoop() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            producerService.sendMessage("Message-" + i + " at " + System.currentTimeMillis());
            Thread.sleep(1000);
        }
        return "Sent 10 messages!";
    }
}