package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRate2Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRate2Consumer.class);

    // @KafkaListener(topics = "t-fixedrate-2")
    public void consumeMessage(String message) {
        LOG.info("t-fixedrate-2 consumed message with auto-offset-reset set to earliest: " + message);
    }

}
