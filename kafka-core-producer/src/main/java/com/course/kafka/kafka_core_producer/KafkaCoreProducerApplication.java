package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.kafka.kafka_core_producer.entity.Employee;
import com.course.kafka.kafka_core_producer.producer.CounterProducer;
import com.course.kafka.kafka_core_producer.producer.Employee2JsonProducer;
import com.course.kafka.kafka_core_producer.producer.HelloKafkaProducer;

@SpringBootApplication
// @EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private final Employee2JsonProducer employeeJsonProducer;

	private final CounterProducer counterProducer;

	private final HelloKafkaProducer helloKafkaProducer;

	public KafkaCoreProducerApplication(Employee2JsonProducer employeeJsonProducer,
			final CounterProducer counterProducer, HelloKafkaProducer helloKafkaProducer) {
		this.helloKafkaProducer = helloKafkaProducer;
		this.counterProducer = counterProducer;
		this.employeeJsonProducer = employeeJsonProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		helloKafkaProducer.sendHello("Mehdi");
	}

	/*
	 * @Override
	 * public void run(String... args) throws Exception {
	 * 
	 * for (int i = 1; i <= 5; i++) {
	 * Employee employee = new Employee(UUID.randomUUID(), "Name " + i,
	 * LocalDate.now().minusYears(20 + i));
	 * employeeJsonProducer.sendMessage(employee);
	 * LOG.info("Sent employee: {}", employee);
	 * TimeUnit.SECONDS.sleep(1);
	 * }
	 * }
	 */

}
