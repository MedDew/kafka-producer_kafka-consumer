package com.course.kafka.kafka_core_producer.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.course.kafka.kafka_core_producer.entity.Commodity;
import com.course.kafka.kafka_core_producer.service.CommodityService;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commodity> generateAllCommodities() {
        return commodityService.generateDummyCommodities();
    }
}
