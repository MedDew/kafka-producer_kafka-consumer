package com.course.kafka.kafka_core_producer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.CarLocation;
import com.course.kafka.kafka_core_producer.producer.CarLocationProducer;

//@Service
public class CarLocationScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(CarLocationScheduler.class);

    private CarLocation carLocationOne;
    private CarLocation carLocationTwo;
    private CarLocation carLocationThree;

    private CarLocationProducer carLocationProducer;

    public CarLocationScheduler(CarLocationProducer carLocationProducer) {
        this.carLocationProducer = carLocationProducer;
        long now = System.currentTimeMillis();
        carLocationOne = new CarLocation("car-1", now, 0);
        carLocationTwo = new CarLocation("car-2", now, 110);
        carLocationThree = new CarLocation("car-3", now, 95);
    }

    @Scheduled(fixedRate = 10000)
    public void generateDummyData() {
        long now = System.currentTimeMillis();
        carLocationOne.setDistance(carLocationOne.getDistance() + 1);
        carLocationTwo.setDistance(carLocationTwo.getDistance() - 1);
        carLocationThree.setDistance(carLocationThree.getDistance() + 1);

        sendCarLocation(carLocationOne);
        sendCarLocation(carLocationTwo);
        sendCarLocation(carLocationThree);
    }

    private void sendCarLocation(CarLocation carLocation) {
        carLocationProducer.sendCarLocation(carLocation);
        LOG.info("Sent Car location : {}", carLocation);
    }

}
