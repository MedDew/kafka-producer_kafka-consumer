package com.course.kafka.kafka_core_producer.producer;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateAIProducer {
    
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FixedRateAIProducer.class);
    private static final String TOPIC = "t-fixedrate";
    private int counter = 0;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        counter++;
        LOG.info("Sending fixed rate message {}", counter);
        kafkaTemplate.send(TOPIC, "Fixed rate message " + counter);
    }
}
