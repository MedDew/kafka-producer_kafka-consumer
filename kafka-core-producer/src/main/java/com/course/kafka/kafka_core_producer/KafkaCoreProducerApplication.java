package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.FoodOrder;
import com.course.kafka.kafka_core_producer.entity.PaymentRequest;
import com.course.kafka.kafka_core_producer.entity.SimpleNumber;
import com.course.kafka.kafka_core_producer.producer.FoodOrderProducer;
import com.course.kafka.kafka_core_producer.producer.PaymentRequestProducer;
import com.course.kafka.kafka_core_producer.producer.SimpleNumberProducer;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private FoodOrderProducer foodOrderProducer;

	private SimpleNumberProducer simpleNumberProducer;

	public KafkaCoreProducerApplication(FoodOrderProducer foodOrderProducer,
			SimpleNumberProducer simpleNumberProducer) {
		this.foodOrderProducer = foodOrderProducer;
		this.simpleNumberProducer = simpleNumberProducer;
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

		for (int i = 100; i < 103; i++) {
			SimpleNumber simpleNumber = new SimpleNumber(i);
			simpleNumberProducer.send(simpleNumber);
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
