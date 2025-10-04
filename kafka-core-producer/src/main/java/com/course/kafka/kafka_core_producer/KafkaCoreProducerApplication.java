package com.course.kafka.kafka_core_producer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.kafka_core_producer.entity.FoodOrder;
import com.course.kafka.kafka_core_producer.entity.Image;
import com.course.kafka.kafka_core_producer.entity.PaymentRequest;
import com.course.kafka.kafka_core_producer.entity.SimpleNumber;
import com.course.kafka.kafka_core_producer.producer.FoodOrderProducer;
import com.course.kafka.kafka_core_producer.producer.ImageProducer;
import com.course.kafka.kafka_core_producer.producer.PaymentRequestProducer;
import com.course.kafka.kafka_core_producer.producer.SimpleNumberProducer;
import com.course.kafka.kafka_core_producer.service.ImageService;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaCoreProducerApplication.class);

	private ImageProducer imageProducer;

	private ImageService imageService;

	public KafkaCoreProducerApplication(ImageProducer imageProducer, ImageService imageService) {
		this.imageProducer = imageProducer;
		this.imageService = imageService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Image image1 = imageService.generateImage("JPG");
		Image image2 = imageService.generateImage("SVG");
		Image image3 = imageService.generateImage("PNG");
		Image image4 = imageService.generateImage("GIF");
		Image image5 = imageService.generateImage("BMP");
		Image image6 = imageService.generateImage("TIFF");

		imageProducer.send(image1, 0);
		imageProducer.send(image2, 0);
		imageProducer.send(image3, 0);

		imageProducer.send(image4, 1);
		imageProducer.send(image5, 1);
		imageProducer.send(image6, 1);

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
