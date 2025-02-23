package com.course.kafka.kafka_core_producer;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.producer.HelloKafkaAIProducer;
import com.course.kafka.kafka_core_producer.producer.HelloKafkaProducer;


@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private HelloKafkaProducer helloKafkaProducer;
	private HelloKafkaAIProducer helloKafkaAIProducer;


	public KafkaCoreProducerApplication(HelloKafkaProducer helloKafkaProducer, HelloKafkaAIProducer helloKafkaAIProducer) {
		this.helloKafkaProducer = helloKafkaProducer;
		this.helloKafkaAIProducer = helloKafkaAIProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		helloKafkaProducer.sendHello("MedGaz "+ ThreadLocalRandom.current().nextInt(1000));
		helloKafkaAIProducer.sendMessage("MedGaz " + UUID.randomUUID().toString());
	}

}
