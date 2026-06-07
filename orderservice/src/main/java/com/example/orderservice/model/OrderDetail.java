package com.example.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
//@Table(name = "orders")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long productId;
    private BigDecimal amount;
    private String status; // PENDING, CONFIRMED
}
