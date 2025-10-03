package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.FoodOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodOrderConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(FoodOrderConsumer.class);
    private static final int MAX_AMOUNT_ORDER = 7;

    private ObjectMapper objectMapper;

    public FoodOrderConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-food-order")
    public void consume(String message) {
        try {
            // Assuming FoodOrder class is available in the consumer module
            FoodOrder foodOrder = objectMapper.readValue(message, FoodOrder.class);
            if (foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
                LOG.error("Amount {} exceeds the maximum allowed amount of {}", foodOrder.getAmount(),
                        MAX_AMOUNT_ORDER);
                throw new IllegalArgumentException("Order amount exceeds the maximum limit of " + MAX_AMOUNT_ORDER);
            }

            LOG.info("Consumed food order: {}", foodOrder);
        } catch (Exception e) {
            LOG.error("Error while consuming message: {}", message, e);
        }
    }

}
