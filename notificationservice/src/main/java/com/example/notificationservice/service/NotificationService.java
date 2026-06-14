package com.example.notificationservice.service;

import com.example.notificationservice.ordereventdto.OrderEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics="order-placed", groupId="notification-group")
    public void handleOrderPlaced(OrderEventDTO evt) {
        log.info("EMAIL → User {} | Order {} | ₹{}",
                evt.getUserId(), evt.getOrderId(), evt.getAmount());
    }
}
