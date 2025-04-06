package com.CrediFlow.ClientCardCredit.config.consumer;

import com.CrediFlow.ClientCardCredit.model.CreditAnalysis;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, CreditAnalysis> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "client-card-credit-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 👇 Aqui garantimos que o deserializer use o tipo fixo, sem header
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.CrediFlow.ClientCardCredit.model.CreditAnalysis");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.CrediFlow.*");

        return new DefaultKafkaConsumerFactory<>(props);
    }



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreditAnalysis> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreditAnalysis> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}


