package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestdto;
import com.example.orderservice.dto.OrderResponsedto;
import org.springframework.stereotype.Service;


public interface OrderService {
    OrderResponsedto placeOrder (OrderRequestdto orderRequestdto);
}
