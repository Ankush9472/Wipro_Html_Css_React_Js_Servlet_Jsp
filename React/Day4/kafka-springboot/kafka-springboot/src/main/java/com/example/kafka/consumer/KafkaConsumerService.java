package com.example.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    // Store received messages so we can verify them via REST API
    private final List<String> receivedMessages = new ArrayList<>();

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        logger.info("Received message: '{}' from topic: 'test-topic'", message);
        receivedMessages.add(message);
    }

    public List<String> getReceivedMessages() {
        return receivedMessages;
    }
}
