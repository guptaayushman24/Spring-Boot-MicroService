package com.example.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderRequestdto {
    private Long userId;
    private Long productId;
    private BigDecimal amount;
    private String status;
}
