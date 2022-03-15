package com.example.kafkaspringboot.controller;

import com.example.kafkaspringboot.model.UserDetails;
import com.example.kafkaspringboot.producer.KafkaPacketProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@Log4j2
public class KafkaController {

    @Value("${spring.kafka.topic.example}")
    private String exampleTopic;

    @Value("${spring.kafka.topic.custom.object}")
    private String customObjectTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaPacketProducer kafkaPacketProducer;

    @PostMapping
    public void pushKafkaPacket() {
        String data = "Hello "+ exampleTopic;
        log.info("Packet published on topic example-topic: {}", data);
        kafkaTemplate.send(exampleTopic, data);
    }

    @PostMapping("/custom-object")
    public void pushCustomKafkaPacket(@RequestBody UserDetails userDetails) {
        log.info("Packet published on topic example-topic: {}", userDetails);
        kafkaPacketProducer.publishPacket(customObjectTopic, userDetails.getClass().getName(), userDetails);
    }

}
