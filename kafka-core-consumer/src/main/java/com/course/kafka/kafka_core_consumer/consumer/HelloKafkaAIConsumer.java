package com.course.kafka.kafka_core_consumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaAIConsumer {

    @KafkaListener(topics = "t-hello")//, groupId = "hello-group"
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + record.value());
    }
}
