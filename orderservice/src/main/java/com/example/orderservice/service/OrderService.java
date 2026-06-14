package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestdto;
import com.example.orderservice.dto.OrderResponsedto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;


public interface OrderService {
    OrderResponsedto placeOrder (OrderRequestdto orderRequestdto) throws JsonProcessingException;
}
