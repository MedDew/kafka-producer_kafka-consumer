package com.course.kafka.kafka_core_producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.PurchaseRequest;
import com.course.kafka.kafka_core_producer.producer.CounterProducer;
import com.course.kafka.kafka_core_producer.producer.Employee2JsonProducer;
import com.course.kafka.kafka_core_producer.producer.HelloKafkaProducer;
import com.course.kafka.kafka_core_producer.producer.PurchaseRequestProducer;

@SpringBootApplication
// @EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private final Employee2JsonProducer employeeJsonProducer;

	private final CounterProducer counterProducer;

	private final HelloKafkaProducer helloKafkaProducer;

	private PurchaseRequestProducer purchaseRequestProducer;

	public KafkaCoreProducerApplication(Employee2JsonProducer employeeJsonProducer,
			final CounterProducer counterProducer, HelloKafkaProducer helloKafkaProducer,
			PurchaseRequestProducer purchaseRequestProducer) {
		this.helloKafkaProducer = helloKafkaProducer;
		this.counterProducer = counterProducer;
		this.employeeJsonProducer = employeeJsonProducer;
		this.purchaseRequestProducer = purchaseRequestProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PurchaseRequest purchaseRequest1 = new PurchaseRequest(UUID.randomUUID(), "REQ-001", 100, "USD");
		PurchaseRequest purchaseRequest2 = new PurchaseRequest(UUID.randomUUID(), "REQ-002", 200, "EUR");
		PurchaseRequest purchaseRequest3 = new PurchaseRequest(UUID.randomUUID(), "REQ-003", 300, "GBP");

		purchaseRequestProducer.sendPurchaseRequest(purchaseRequest1);
		purchaseRequestProducer.sendPurchaseRequest(purchaseRequest2);
		purchaseRequestProducer.sendPurchaseRequest(purchaseRequest3);

		// Simulating a duplicate message recorded into t-purchase-request topic
		purchaseRequestProducer.sendPurchaseRequest(purchaseRequest1);

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
