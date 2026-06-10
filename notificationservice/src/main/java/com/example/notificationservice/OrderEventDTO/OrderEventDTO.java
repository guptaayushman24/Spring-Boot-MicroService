package com.example.notificationservice.OrderEventDTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderEventDTO {
        private String orderEventId;
        private Long orderId;
        private Long userId;
        private Long productId;
        private BigDecimal amount;
}
