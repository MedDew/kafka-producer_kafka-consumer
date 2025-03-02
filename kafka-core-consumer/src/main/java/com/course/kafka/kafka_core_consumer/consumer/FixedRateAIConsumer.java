package com.course.kafka.kafka_core_consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateAIConsumer {

    // Using copilot to implement a consumer that consumes messages from a Kafka
    // topic t-fixedrate
    // The consumer should print the message to the console
    // @KafkaListener(topics = "t-fixedrate")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }

}
