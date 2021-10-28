package com.learnspringboot.demo.config;

import com.learnspringboot.demo.constant.KafkaConfigConstant;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguartion {

//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.KAFKA_LOCAL_SERVER_CONFIG);
//        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.learnspringboot.demo.entity");
//
//        configMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        configMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//        configMap.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
//                "%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "admin", "admin-secret"
//        ));
//        return new DefaultKafkaProducerFactory<>(configMap);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.KAFKA_LOCAL_SERVER_CONFIG);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfigConstant.GROUP_ID_JSON);
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.learnspringboot.demo.entity");

//        configMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        configMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//        configMap.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
//                "%s required username=\"%s\" " + "password=\"%s\";", PlainLoginModule.class.getName(), "admin", "admin-secret"
//        ));
        return new DefaultKafkaConsumerFactory<>(configMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
