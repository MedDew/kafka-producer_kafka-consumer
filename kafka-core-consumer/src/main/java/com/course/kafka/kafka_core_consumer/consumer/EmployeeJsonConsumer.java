package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

// @Service
public class EmployeeJsonConsumer {

    private final ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    public EmployeeJsonConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-employee-2")
    public void consumeMessage(String message) {
        try {
            var employee = objectMapper.readValue(message, Employee.class);
            LOG.info("Employee is {}", employee);
        } catch (Exception e) {
            LOG.error("Error consuming message", e);
        }
    }

}
