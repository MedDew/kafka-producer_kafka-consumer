package com.course.kafka.kafka_core_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.Image;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    private ObjectMapper objectMapper;

    public ImageProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(Image image, int partition) {
        // try {
        // String imageJson = objectMapper.writeValueAsString(image);
        // kafkaTemplate.send("t-image", partition, image.getType(), imageJson);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
}
