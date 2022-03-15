package com.example.kafkaspringboot.consumer;

import com.example.kafkaspringboot.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Log4j2
public class KafkaListeners<T> {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "example-topic", groupId = "groupId")
    void sampleTopicListener(String data) {
       log.info("Packet received on topic example-topic: {}", data);
    }

    @KafkaListener(topics = "custom-object-topic", groupId = "groupId")
    void customObjectTopicListener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException, ClassNotFoundException {
        //generic dto type conversion check to class provided in key
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) Class.forName(consumerRecord.key());
        T data = objectMapper.readValue(consumerRecord.value(), clazz);
        log.info("Packet received on topic custom-object-topic: {}", data);

        //specific dto type conversion for further processing
        UserDetails userDetails = objectMapper.readValue(consumerRecord.value(), UserDetails.class);
        log.info("firstName: {}", userDetails.getFirstName());
        if(Objects.nonNull(userDetails.getAddress())) {
            log.info("address: {}", userDetails.getAddress());
        }
    }

}
