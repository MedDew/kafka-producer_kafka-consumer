package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class HelloKafkaAIProducer {
    private static final String TOPIC = "t-hello";
    private KafkaTemplate<String, String> kafkaTemplate;

    public HelloKafkaAIProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, "Hello "+message);
    }

}
