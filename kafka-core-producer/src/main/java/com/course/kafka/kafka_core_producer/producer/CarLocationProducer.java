package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarLocationProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;;

    public CarLocationProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCarLocation(CarLocation carLocation) {
        try {
            String carLocationJson = objectMapper.writeValueAsString(carLocation);
            kafkaTemplate.send("t-location", carLocation.getCarId(), carLocationJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
