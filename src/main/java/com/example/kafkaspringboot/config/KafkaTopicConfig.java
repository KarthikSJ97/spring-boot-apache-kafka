package com.example.kafkaspringboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.example}")
    private String exampleTopic;

    @Value("${spring.kafka.topic.custom.object}")
    private String customObjectTopic;

    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name(exampleTopic).build();
    }

    @Bean
    public NewTopic customObjectTopic() {
        return TopicBuilder.name(customObjectTopic).build();
    }
}
