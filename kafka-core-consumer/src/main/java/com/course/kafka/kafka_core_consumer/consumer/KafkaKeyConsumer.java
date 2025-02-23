package com.course.kafka.kafka_core_consumer.consumer;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaKeyConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaKeyConsumer.class);

    @KafkaListener(topics = "t-multi-partitions")
    public void consumeMessage(ConsumerRecord<String, String> record) throws InterruptedException {
        LOG.info("Key = {} | Value = {}" , record.key() , record.value());
        TimeUnit.SECONDS.sleep(1);
    }
}
