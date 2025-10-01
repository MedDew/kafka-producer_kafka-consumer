package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PurchaseRequestProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public PurchaseRequestProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPurchaseRequest(PurchaseRequest purchaseRequest) {
        // Serialize the object to JSON
        String purchaseRequestJson;
        try {
            purchaseRequestJson = objectMapper.writeValueAsString(purchaseRequest);
            kafkaTemplate.send("t-purchase-request", purchaseRequest.getRequestNumber(), purchaseRequestJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
