package com.course.kafka.kafka_core_producer;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.producer.HelloKafkaProducer;


@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private HelloKafkaProducer helloKafkaProducer;

	public KafkaCoreProducerApplication(HelloKafkaProducer helloKafkaProducer) {
		this.helloKafkaProducer = helloKafkaProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		helloKafkaProducer.sendHello("MedGaz "+ ThreadLocalRandom.current().nextInt(1000));
	}

}
