package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.PurchaseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

@Service
public class PurchaseRequestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseRequestConsumer.class);

    private ObjectMapper objectMapper;

    private Cache<String, Boolean> cachePurchaserequest;

    public PurchaseRequestConsumer(ObjectMapper objectMapper,
            @Qualifier("cachePurchaserequest") Cache<String, Boolean> cachePurchaserequest) {
        this.objectMapper = objectMapper;
        this.cachePurchaserequest = cachePurchaserequest;
    }

    private boolean isExistsInCache(String requestNumber) {
        Boolean exists = cachePurchaserequest.getIfPresent(requestNumber);
        return exists != null && exists;
    }

    @KafkaListener(topics = "t-purchase-request")
    public void listen(String json) {
        try {

            PurchaseRequest purchaseRequest = objectMapper.readValue(json, PurchaseRequest.class);
            if (isExistsInCache(purchaseRequest.getRequestNumber())) {
                LOG.warn("Purchase request already exists in cache : {}", purchaseRequest.getRequestNumber());
                return;
            }

            LOG.info("Processing purchase request : {}", purchaseRequest.getRequestNumber());
            cachePurchaserequest.put(purchaseRequest.getRequestNumber(), true);

        } catch (Exception e) {
            LOG.error("Error processing purchase request", e);
        }
    }
}
