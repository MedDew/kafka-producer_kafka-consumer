package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CounterProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String T_COUNTER_TOPIC = "t-counter";

    public CounterProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(int number) {
        for (int i = 0; i < number; i++) {
            String message = "Data : " + i;
            kafkaTemplate.send(T_COUNTER_TOPIC, message);
        }

    }

}
