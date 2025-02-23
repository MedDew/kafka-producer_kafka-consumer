package com.course.kafka.kafka_core_producer;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.kafka.kafka_core_producer.producer.KafkaKeyProducer;


@SpringBootApplication
//@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {


	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	@Autowired
 	private KafkaKeyProducer kafkaKeyProducer;

    public KafkaCoreProducerApplication(KafkaKeyProducer kafkaKeyProducer) {
        this.kafkaKeyProducer = kafkaKeyProducer;
    }

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 1; i <= 10_000; i++) {
            String key = "key-" + i;
            String message = "Message " + i;

			LOG.info("Sending message with key: {} and message: {}", key, message);
            kafkaKeyProducer.sendMessage(key, message);

			TimeUnit.SECONDS.sleep(1);
        }

	}

}
