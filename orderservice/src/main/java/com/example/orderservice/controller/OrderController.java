package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestdto;
import com.example.orderservice.dto.OrderResponsedto;
import com.example.orderservice.serviceimpl.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;
    @PostMapping("/orders")
    public ResponseEntity<OrderResponsedto> placeOrder (@RequestBody OrderRequestdto orderRequestdto) throws JsonProcessingException {
        return ResponseEntity.ok(orderServiceImpl.placeOrder(orderRequestdto));
    }
}
