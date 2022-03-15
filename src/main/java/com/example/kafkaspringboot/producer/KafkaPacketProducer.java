package com.example.kafkaspringboot.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@Component
@Log4j2
public class KafkaPacketProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void publishPacket(String topic, String className, Object value) {
        ProducerRecord<String, String> producerRecord = null;
        try {
            producerRecord = new ProducerRecord<>(topic, className, objectMapper.writeValueAsString(value));
        } catch (Exception e) {
            log.error("Failed to serialize message to send to topic: " + topic, e);
        }
        publishPacket(producerRecord);
    }

    public void publishPacket(ProducerRecord<String, String> producerRecord) {
        if (Objects.nonNull(producerRecord)) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Successfully sent packet to topic: {} with data: {}", producerRecord.topic(), producerRecord.value());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Packet failed to publish to topic: {} with error: {}", producerRecord.topic(), ex.getMessage());
                }
            });
        }
    }

}
