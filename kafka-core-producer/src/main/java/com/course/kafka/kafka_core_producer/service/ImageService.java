package com.course.kafka.kafka_core_producer.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.Image;

@Service
public class ImageService {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    public Image generateImage(String type) {
        String name = "image-" + COUNTER.incrementAndGet();
        long size = ThreadLocalRandom.current().nextLong(1000, 10_001);

        return new Image(name, size, type);
    }
}
