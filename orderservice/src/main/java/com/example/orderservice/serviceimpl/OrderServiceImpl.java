package com.example.orderservice.serviceimpl;

import com.example.orderservice.dto.*;
import com.example.orderservice.model.OrderDetail;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
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
    @Override
    public OrderResponsedto placeOrder(OrderRequestdto orderRequestdto) {
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

        OrderDetail savedOrder = orderRepository.save(orderDetail);
        // After saving the order push the order detail in the 'order-placed'
        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setOrderEventId(orderEventDTO.getOrderEventId());
        orderEventDTO.setOrderId(savedOrder.getId());
        orderEventDTO.setAmount(savedOrder.getAmount());
        orderEventDTO.setUserId(savedOrder.getUserId());
        orderEventDTO.setProductId(savedOrder.getProductId());

        kafkaTemplate.send("order-placed",orderEventDTO);

        orderResponsedto.setUserId(orderDetail.getUserId());
        orderResponsedto.setProductId(orderDetail.getProductId());
        orderResponsedto.setAmount(orderDetail.getAmount());
        orderResponsedto.setStatus(orderDetail.getStatus());

        log.info("Event published: {}", orderResponsedto.getUserId());

        return orderResponsedto;
    }
}
