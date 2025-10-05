package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);

    private ObjectMapper objectMapper;

    public InvoiceConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-invoice", concurrency = "2", containerFactory = "incoiceDltContainerFactory")
    public void consume(String invoiceJson) throws JsonMappingException, JsonProcessingException {

        Invoice invoice = objectMapper.readValue(invoiceJson, Invoice.class);

        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException("Invoice amount is less than 1 : " + invoice.getAmount()
                    + ", for invoice : " + invoice.getInvoiceNumber());
        }

        LOG.info("Consuming invoice: {}", invoiceJson);
    }
}
