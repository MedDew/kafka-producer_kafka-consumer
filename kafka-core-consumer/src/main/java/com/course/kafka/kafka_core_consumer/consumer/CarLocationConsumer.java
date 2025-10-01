package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarLocationConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(CarLocationConsumer.class);

    private ObjectMapper objectMapper;

    public CarLocationConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-location", groupId = "group-location-all")
    public void listenAll(String message) throws JsonMappingException, JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        LOG.info("listenAll() Received Car location : {}", carLocation);
    }

    @KafkaListener(topics = "t-location", groupId = "group-location-far", containerFactory = "locationFarContainerFactory")
    public void listenFar(String message) throws JsonMappingException, JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        // if (carLocation.getDistance() <= 100) {
        LOG.info("listenFar() Received Car location : {}", carLocation);
        // }
    }

}
