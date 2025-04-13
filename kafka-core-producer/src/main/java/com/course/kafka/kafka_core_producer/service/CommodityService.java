package com.course.kafka.kafka_core_producer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.course.kafka.kafka_core_producer.entity.Commodity;

@Service
public class CommodityService {

    private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();

    private static final String COPPER = "Copper";

    private static final String GOLD = "Gold";

    private static final double MIN_ADJUSTMENT = 0.95;
    private static final double MAX_ADJUSTMENT = 1.05;

    static {
        var timestamp = System.currentTimeMillis();
        COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 10_000, "ton", timestamp));
        COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 2500, "ounce", timestamp));
    }

    public Commodity generateDummyCommodity(String name) {
        if (!COMMODITY_BASE.containsKey(name)) {
            throw new IllegalArgumentException("Invalid Commodity name : " + name);
        }

        Commodity baseCommodity = COMMODITY_BASE.get(name);
        double basePrice = baseCommodity.getPrice();

        double adjustment = Math.random() * (MAX_ADJUSTMENT - MIN_ADJUSTMENT) + MIN_ADJUSTMENT;
        double adjustedPrice = basePrice * adjustment;

        long timestamp = System.currentTimeMillis();

        return new Commodity(name, adjustedPrice, baseCommodity.getMeasurement(), timestamp);
    }

    public List<Commodity> generateDummyCommodities() {
        return COMMODITY_BASE.keySet().stream().map(this::generateDummyCommodity).toList();
    }

}
