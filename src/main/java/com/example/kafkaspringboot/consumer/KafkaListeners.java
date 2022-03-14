package com.example.kafkaspringboot.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaListeners {

    @KafkaListener(topics = "example-topic", groupId = "groupId")
    void sampleTopicListener(String data) {
       log.info("Packet received on topic example-topic: {}", data);
    }

}
