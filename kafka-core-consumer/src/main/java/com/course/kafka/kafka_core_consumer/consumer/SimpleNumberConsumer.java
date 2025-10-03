package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SimpleNumberConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleNumberConsumer.class);

    private ObjectMapper objectMapper;

    public SimpleNumberConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-simple-number")
    public void consume(String message) throws JsonMappingException, JsonProcessingException {
        SimpleNumber simpleNumber = objectMapper.readValue(message, SimpleNumber.class);

        if (simpleNumber.getNumber() % 2 != 0) {
            throw new IllegalArgumentException("Odd number exception : " + simpleNumber.getNumber());
        }
        LOG.info("Consumed Even SimpleNumber: {}", simpleNumber);
    }
}
