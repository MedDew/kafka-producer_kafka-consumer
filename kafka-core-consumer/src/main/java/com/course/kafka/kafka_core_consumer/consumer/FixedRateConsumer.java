package com.course.kafka.kafka_core_consumer.consumer;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumer {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FixedRateConsumer.class);

    @KafkaListener(topics = "t-fixedrate")
    public void consume(String message) {
        LOG.info("Consumed message: {}", message);
    }

}
