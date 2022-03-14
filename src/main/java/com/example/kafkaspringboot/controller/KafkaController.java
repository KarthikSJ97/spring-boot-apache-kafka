package com.example.kafkaspringboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Log4j2
public class KafkaController {

    @Value("${spring.kafka.topic.example}")
    private String exampleTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void pushKafkaPacket() {
        String data = "Hello "+ exampleTopic;
        log.info("Packet published on topic example-topic: {}", data);
        kafkaTemplate.send(exampleTopic, data);
    }

}
