package com.course.kafka.kafka_core_producer.scheduler;

import java.util.Arrays;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.course.kafka.kafka_core_producer.entity.Commodity;
import com.course.kafka.kafka_core_producer.producer.CommodityProducer;

@Component
public class CommodityScheduler {

    private RestTemplate restTemplate = new RestTemplate();

    private CommodityProducer commodityProducer;

    private static final String COMMODITY_API_URL = "http://localhost:8080/api/commodity/v1/all";

    public CommodityScheduler(CommodityProducer commodityProducer) {
        this.commodityProducer = commodityProducer;
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleCommodityGeneration() {
        // Call the API to get a new commodity
        Commodity[] commodities = restTemplate.getForObject(COMMODITY_API_URL, Commodity[].class);
        // Send the commodity to Kafka
        if (commodities != null) {
            Arrays.stream(commodities).forEach(commodityProducer::sendMessage);
        }
    }

}
