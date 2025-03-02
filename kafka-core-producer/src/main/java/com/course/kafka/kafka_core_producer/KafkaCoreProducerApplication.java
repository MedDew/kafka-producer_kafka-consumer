package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.Employee;
import com.course.kafka.kafka_core_producer.producer.Employee2JsonProducer;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private final Employee2JsonProducer employeeJsonProducer;

	public KafkaCoreProducerApplication(Employee2JsonProducer employeeJsonProducer) {
		this.employeeJsonProducer = employeeJsonProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee(UUID.randomUUID(), "Name " + i, LocalDate.now().minusYears(20 + i));
			employeeJsonProducer.sendMessage(employee);
			LOG.info("Sent employee: {}", employee);
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
