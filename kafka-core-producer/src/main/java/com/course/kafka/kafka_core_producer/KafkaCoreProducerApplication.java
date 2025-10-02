package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.PaymentRequest;
import com.course.kafka.kafka_core_producer.producer.PaymentRequestProducer;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private PaymentRequestProducer paymentRequestProducer;

	public KafkaCoreProducerApplication(PaymentRequestProducer paymentRequestProducer) {
		this.paymentRequestProducer = paymentRequestProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PaymentRequest paymentRequest1 = new PaymentRequest(100, "USD", "987-654", "Payment for invoice 789",
				LocalDate.now().plusDays(2));
		PaymentRequest paymentRequest2 = new PaymentRequest(200, "CAN", "123-456", "Payment for invoice 123",
				LocalDate.now());
		PaymentRequest paymentRequest3 = new PaymentRequest(300, "EUR", "789-012", "Payment for invoice 456",
				LocalDate.now().plusDays(5));
		PaymentRequest paymentRequest4 = new PaymentRequest(400, "EUR", "789-012", "Payment for invoice 456",
				LocalDate.now().plusDays(10));
		PaymentRequest paymentRequest5 = new PaymentRequest(500, "EUR", "789-012", "Payment for invoice 456",
				LocalDate.now().plusDays(15));
		PaymentRequest paymentRequest6 = new PaymentRequest(600, "EUR", "789-012", "Payment for invoice 456",
				LocalDate.now().plusDays(20));

		paymentRequestProducer.sendPaymentRequest(paymentRequest1);
		paymentRequestProducer.sendPaymentRequest(paymentRequest2);
		paymentRequestProducer.sendPaymentRequest(paymentRequest3);
		paymentRequestProducer.sendPaymentRequest(paymentRequest4);
		paymentRequestProducer.sendPaymentRequest(paymentRequest5);
		paymentRequestProducer.sendPaymentRequest(paymentRequest6);

		// Simulating a duplicate message recorded into t-payment-request topic
		paymentRequestProducer.sendPaymentRequest(paymentRequest1);
		paymentRequestProducer.sendPaymentRequest(paymentRequest2);

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
