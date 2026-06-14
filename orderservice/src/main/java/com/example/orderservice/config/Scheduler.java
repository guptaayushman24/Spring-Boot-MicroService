package com.example.orderservice.config;

import com.example.orderservice.dto.OrderEventDTO;
import com.example.orderservice.repository.OutBoxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final OutBoxEventRepository outBoxEventRepository;
    private final KafkaTemplate<String, OrderEventDTO> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void pollAndPublish(){
        ObjectMapper objectMapper = new ObjectMapper();
        outBoxEventRepository.findUnpublished().forEach(e -> {
            try {
                OrderEventDTO orderEventDTO =
                        objectMapper.readValue(e.getPayload(), OrderEventDTO.class);
                        kafkaTemplate.send("order-placed", orderEventDTO);
                        e.setPublishedAt(LocalDateTime.now());
                        outBoxEventRepository.save(e);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
