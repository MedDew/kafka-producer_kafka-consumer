package com.course.kafka.kafka_core_consumer.consumer;

import org.apache.kafka.common.protocol.types.Field.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_consumer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ImageConsumer.class);

    private ObjectMapper objectMapper;

    public ImageConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t-image", concurrency = "2", containerFactory = "imageRetryContainerFactory")
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition)
            throws JsonMappingException, JsonProcessingException {

        // Image image = objectMapper.readValue(message, Image.class);

        // if ("SVG".equalsIgnoreCase(image.getType())) {
        // throw new IllegalArgumentException("SVG images are not supported");
        // }
        // LOG.info("Image received: {} from partition {}", image, partition);

    }

}
