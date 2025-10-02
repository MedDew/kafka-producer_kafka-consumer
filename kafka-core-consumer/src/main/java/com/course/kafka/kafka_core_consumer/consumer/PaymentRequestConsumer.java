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
public class PaymentRequestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestConsumer.class);

    private ObjectMapper objectMapper;

    private Cache<String, Boolean> cachePaymentRequest;

            
    public PaymentRequestConsumer(ObjectMapper objectMapper,@Qualifier("cachePaymentRequest") Cache<String, Boolean> cachePaymentRequest) {
        this.objectMapper = objectMapper;
        this.cachePaymentRequest = cachePaymentRequest;
    }

    private boolean isExistsInCache(String key) {
        return cachePaymentRequest.getIfPresent(key) != null;
    }

    @KafkaListener(topics = "t-payment-request")
    public void listen(String json) {
        try {

            PaymentRequest paymentRequest = objectMapper.readValue(json, PaymentRequest.class);
            String cacheKey = paymentRequest.calculateHash();

            if (isExistsInCache(cacheKey)) {
                LOG.warn("Payment request already exists in cache : {}", paymentRequest);
                return;
            }

            LOG.info("Processing Payment request : {}", paymentRequest);
            cachePaymentRequest.put(cacheKey, true);

        } catch (Exception e) {
            LOG.error("Error processing Payment request", e);
        }
    }
}
