package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentRequestProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public PaymentRequestProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPaymentRequest(PaymentRequest paymentRequest) {
        String paymentRequestJson;
        try {
            // Serialize the object to JSON
            paymentRequestJson = objectMapper.writeValueAsString(paymentRequest);
            kafkaTemplate.send("t-payment-request", paymentRequestJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
