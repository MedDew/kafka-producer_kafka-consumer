package com.course.kafka.kafka_core_producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.Invoice;
import com.course.kafka.kafka_core_producer.producer.InvoiceProducer;
import com.course.kafka.kafka_core_producer.service.InvoiceService;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private InvoiceProducer invoiceProducer;

	private InvoiceService invoiceService;

	public KafkaCoreProducerApplication(InvoiceProducer invoiceProducer, InvoiceService invoiceService) {
		this.invoiceProducer = invoiceProducer;
		this.invoiceService = invoiceService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Invoice invoice = invoiceService.generateInvoice();

			if (i > 5) {
				invoice.setAmount(0d);
			}

			invoiceProducer.sendinvoice(invoice);
		}
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
