package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SimpleNumberProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    private ObjectMapper objectMapper;

    public SimpleNumberProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
        String simpleNumberJson = objectMapper.writeValueAsString(simpleNumber);
        kafkaTemplate.send("t-simple-number", simpleNumberJson);
    }
}
