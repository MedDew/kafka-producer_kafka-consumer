package com.course.kafka.kafka_core_consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaConsumer {

    // @KafkaListener(topics = "t-hello")
    public void consumeHello(String message) {
        System.out.println("Consumed message: " + message);
    }

}
