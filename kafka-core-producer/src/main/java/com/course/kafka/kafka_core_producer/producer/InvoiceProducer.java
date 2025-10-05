package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper;

    public InvoiceProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendinvoice(Invoice invoice) {
        try {
            String invoiceJson = objectMapper.writeValueAsString(invoice);
            kafkaTemplate.send("t-invoice", (int) invoice.getAmount() % 2, invoice.getInvoiceNumber(), invoiceJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
