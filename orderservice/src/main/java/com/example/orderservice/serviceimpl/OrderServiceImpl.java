package com.example.orderservice.serviceimpl;

import com.example.orderservice.dto.*;
import com.example.orderservice.model.OrderDetail;
import com.example.orderservice.model.OutBoxEvent;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.OutBoxEventRepository;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserClientService userClientService;
    private final KafkaTemplate kafkaTemplate;
    private final OutBoxEventRepository outBoxEventRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    @Transactional
    public OrderDetail saveOrderinDB(OrderDetail orderDetail) throws JsonProcessingException {
        OrderDetail savedOrder = orderRepository.save(orderDetail);

        OrderEventDTO eventDTO = new OrderEventDTO();
        eventDTO.setOrderId(savedOrder.getId());
        eventDTO.setUserId(savedOrder.getUserId());
        eventDTO.setProductId(savedOrder.getProductId());
        eventDTO.setAmount(savedOrder.getAmount());

        outBoxEventRepository.save(OutBoxEvent.builder()
                .eventType("ORDER_PLACED")
                .payload(toJson(eventDTO))
                .build());
        return savedOrder;
    }
    @Override
    public OrderResponsedto placeOrder(OrderRequestdto orderRequestdto) throws JsonProcessingException {
        //Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        OrderResponsedto orderResponsedto = new OrderResponsedto();
        orderDetail.setUserId(orderRequestdto.getUserId());
        orderDetail.setProductId(orderRequestdto.getProductId());
        orderDetail.setAmount(orderRequestdto.getAmount());
        orderDetail.setStatus(orderRequestdto.getStatus());

        // Before saving the order check userExist or not
        UserExistRequestdto userExistRequestdto = new UserExistRequestdto();
        userExistRequestdto.setUserId(orderRequestdto.getUserId());
        //UserExistResponsedto userExistResponsedto = userClient.userExist(userExistRequestdto);
        UserExistResponsedto userExistResponsedto = userClientService.getUser(userExistRequestdto);
        if (userExistResponsedto==null){
            throw new RuntimeException("User not found please register the user then place the order");
        }

        // OrderDetail savedOrder = orderRepository.save(orderDetail);
        OrderDetail savedOrder = saveOrderinDB (orderDetail);

        orderResponsedto.setUserId(orderDetail.getUserId());
        orderResponsedto.setProductId(orderDetail.getProductId());
        orderResponsedto.setAmount(orderDetail.getAmount());
        orderResponsedto.setStatus(orderDetail.getStatus());

        log.info("Event published: {}", orderResponsedto.getUserId());

        return orderResponsedto;
    }
}
