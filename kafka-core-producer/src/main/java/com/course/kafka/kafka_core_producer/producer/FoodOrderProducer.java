package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.FoodOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodOrderProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public FoodOrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendFoodOrder(FoodOrder foodOrder) {
        // try {
        // String foodOrderJson = objectMapper.writeValueAsString(foodOrder);
        // kafkaTemplate.send("t-food-order", foodOrderJson);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

}
