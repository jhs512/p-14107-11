package com.back.global.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object o) {
        o.getClass();

        kafkaTemplate.send(o.getClass().getSimpleName(), o);
    }
}
