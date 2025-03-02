package com.course.kafka.kafka_core_producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Employee2JsonProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Employee employee) {
        // convert (serialize) Employee object to JSON and publist to t-employee topic

        try {
            String json = objectMapper.writeValueAsString(employee);
            kafkaTemplate.send("t-employee-2", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
