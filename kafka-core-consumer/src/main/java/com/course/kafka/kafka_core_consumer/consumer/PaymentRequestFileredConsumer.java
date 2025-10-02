package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

@Service
public class PaymentRequestFileredConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestConsumer.class);

    private ObjectMapper objectMapper;

    private Cache<String, Boolean> cachePaymentRequest;

    public PaymentRequestFileredConsumer(ObjectMapper objectMapper,
            @Qualifier("cachePaymentRequest") Cache<String, Boolean> cachePaymentRequest) {
        this.objectMapper = objectMapper;
        this.cachePaymentRequest = cachePaymentRequest;
    }

    @KafkaListener(topics = "t-payment-request", containerFactory = "paymentRequestContainerFactory", groupId = "payment-request-filtered-consumer-group")
    public void listen(String json) {
        try {

            PaymentRequest paymentRequest = objectMapper.readValue(json, PaymentRequest.class);
            String cacheKey = paymentRequest.calculateHash();

            LOG.info("Processing Payment request from PaymentRequestFileredConsumer : {}", paymentRequest);
            cachePaymentRequest.put(cacheKey, true);

        } catch (Exception e) {
            LOG.error("Error processing Payment request fom PaymentRequestFileredConsumer", e);
        }
    }
}
