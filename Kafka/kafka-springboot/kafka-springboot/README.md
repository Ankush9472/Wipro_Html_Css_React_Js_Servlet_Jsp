# Spring Boot + Kafka Demo

## Prerequisites
- Java 17+
- Maven
- Kafka running on localhost:9092

## Project Structure
```
kafka-demo/
├── pom.xml
└── src/main/
    ├── java/com/example/kafka/
    │   ├── KafkaDemoApplication.java       ← Main class
    │   ├── producer/
    │   │   └── KafkaProducerService.java   ← Sends messages
    │   ├── consumer/
    │   │   └── KafkaConsumerService.java   ← Receives messages
    │   └── controller/
    │       └── KafkaController.java        ← REST API endpoints
    └── resources/
        └── application.properties          ← Kafka config
```

## Step 1: Make sure Kafka is running
In your Kafka terminal (C:\kafka):
```
bin\windows\kafka-server-start.bat config\server.properties
```

## Step 2: Run the Spring Boot app
```
mvn spring-boot:run
```
OR in IntelliJ: Run KafkaDemoApplication.java

## Step 3: Test with these URLs

### Health Check
GET http://localhost:8080/kafka/health

### Send a Message
POST http://localhost:8080/kafka/send?message=HelloKafka

### View Received Messages
GET http://localhost:8080/kafka/messages

## Step 4: Verify in browser or Postman

1. Open browser → http://localhost:8080/kafka/health
   Expected: "Kafka Spring Boot app is running!"

2. Send message via Postman or curl:
   curl -X POST "http://localhost:8080/kafka/send?message=HelloKafka"
   Expected: "Message sent: HelloKafka"

3. Check received messages:
   curl http://localhost:8080/kafka/messages
   Expected: ["HelloKafka"]

4. Check Spring Boot console logs — you should see:
   Sending message: 'HelloKafka' to topic: 'test-topic'
   Message sent successfully!
   Received message: 'HelloKafka' from topic: 'test-topic'

## What happens internally
Producer (REST call) → Kafka topic 'test-topic' → Consumer (listener) → stored in list
