package com.course.kafka.kafka_core_consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.backoff.FixedBackOff;

import com.course.kafka.kafka_core_consumer.entity.CarLocation;
import com.course.kafka.kafka_core_consumer.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(SslBundles sslBundles) {
        var properties = kafkaProperties.buildConsumerProperties(sslBundles);
        properties.put(ConsumerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, "40000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "locationNearContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> locationFarContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            SslBundles sslBundles,
            ObjectMapper objectMapper) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory(sslBundles));

        // Filter out | skip | discard records whose distance is less than or equal to
        // 100
        // Just shows car over 100
        factory.setRecordFilterStrategy(record -> {
            CarLocation carLocation;
            try {
                carLocation = objectMapper.readValue(record.value().toString(), CarLocation.class);
            } catch (JsonProcessingException e) {
                return false;
            }

            return carLocation.getDistance() <= 100;
        });

        return factory;
    }

    @Bean(name = "paymentRequestContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> paymentRequestContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            SslBundles sslBundles,
            ObjectMapper objectMapper,
            @Qualifier("cachePaymentRequest") Cache<String, Boolean> cachePaymentRequest) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory(sslBundles));

        factory.setRecordFilterStrategy(record -> {
            PaymentRequest paymentRequest;
            try {
                paymentRequest = objectMapper.readValue(record.value().toString(), PaymentRequest.class);
                if (cachePaymentRequest.getIfPresent(paymentRequest.calculateHash()) != null) {
                    LOG.info("Skipping duplicate payment request: {}", paymentRequest);
                }
                return cachePaymentRequest.getIfPresent(paymentRequest.calculateHash()) != null;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            }
        });

        return factory;
    }

    @Bean(name = "imageRetryContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            SslBundles sslBundles) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory(sslBundles));
        factory.setConcurrency(2);
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));

        return factory;
    }
}
