package com.course.kafka.kafka_core_producer.producer;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateProducer {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FixedRateProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private int i = 0;

    public FixedRateProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        LOG.info("Sending fixed rate message {}", i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate message " + i);
    }

}
