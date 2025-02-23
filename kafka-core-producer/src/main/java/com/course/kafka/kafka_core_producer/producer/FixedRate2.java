package com.course.kafka.kafka_core_producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRate2 {

    private static final Logger log = LoggerFactory.getLogger(FixedRate2.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private int i = 0;

    public FixedRate2(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        log.info("Sending message to t-fixedrate-2 topic: {}", "I ::> " + i);
        kafkaTemplate.send("t-fixedrate-2",  "Fixed rate 2 => " + i);
    }


}
