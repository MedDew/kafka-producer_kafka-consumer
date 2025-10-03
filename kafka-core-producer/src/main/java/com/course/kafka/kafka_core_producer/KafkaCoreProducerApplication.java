package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.FoodOrder;
import com.course.kafka.kafka_core_producer.entity.PaymentRequest;
import com.course.kafka.kafka_core_producer.producer.FoodOrderProducer;
import com.course.kafka.kafka_core_producer.producer.PaymentRequestProducer;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private FoodOrderProducer foodOrderProducer;

	public KafkaCoreProducerApplication(FoodOrderProducer foodOrderProducer) {
		this.foodOrderProducer = foodOrderProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FoodOrder chickenOrder = new FoodOrder(3, "Chicken");
		FoodOrder fishOrder = new FoodOrder(10, "Fish");
		FoodOrder pizzaOrder = new FoodOrder(5, "Pizza");

		foodOrderProducer.sendFoodOrder(chickenOrder);
		foodOrderProducer.sendFoodOrder(fishOrder);
		foodOrderProducer.sendFoodOrder(pizzaOrder);
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
