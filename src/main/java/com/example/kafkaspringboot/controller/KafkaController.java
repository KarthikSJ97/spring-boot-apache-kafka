package com.example.kafkaspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Value("${spring.kafka.topic.example}")
    private String exampleTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void pushKafkaPacket() {
        kafkaTemplate.send(exampleTopic, "Hello "+ exampleTopic);
    }

}
