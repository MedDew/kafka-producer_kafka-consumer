package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class CommodityNotificationConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

    @KafkaListener(topics = "t-commodity", groupId = "consumer-group-notification")
    private void listen(String message) {
        try {
            Commodity commodity = objectMapper.readValue(message, Commodity.class);
            LOG.info("Notification consumer: {}", commodity);
        } catch (Exception e) {
            LOG.error("Error processing message: {}", message, e);
        }
    }

}
