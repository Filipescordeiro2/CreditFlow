package com.CrediFlow.CreditAnalysis.config.producer;

import com.CrediFlow.CreditAnalysis.dto.CreditAnalysisResponse;
import com.CrediFlow.CreditAnalysis.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final ObjectMapper objectMapper = createObjectMapper();

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Suporte a LocalDateTime
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO-8601
        return mapper;
    }

    private <T> ProducerFactory<String, T> producerFactory(Class<T> valueType) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        JsonSerializer<T> jsonSerializer = new JsonSerializer<>(objectMapper);
        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), jsonSerializer);
    }

    @Bean
    public KafkaTemplate<String, CreditAnalysisResponse> kafkaTemplateCreditAnalysisResponse() {
        return new KafkaTemplate<>(producerFactory(CreditAnalysisResponse.class));
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplateOrder() {
        return new KafkaTemplate<>(producerFactory(Order.class));
    }
}
