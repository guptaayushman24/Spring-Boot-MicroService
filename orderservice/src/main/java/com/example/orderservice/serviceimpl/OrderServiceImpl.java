package com.example.orderservice.serviceimpl;

import com.example.orderservice.dto.OrderRequestdto;
import com.example.orderservice.dto.OrderResponsedto;
import com.example.orderservice.dto.UserExistRequestdto;
import com.example.orderservice.dto.UserExistResponsedto;
import com.example.orderservice.model.OrderDetail;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserClientService userClientService;
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

        orderRepository.save(orderDetail);

        orderResponsedto.setUserId(orderDetail.getUserId());
        orderResponsedto.setProductId(orderDetail.getProductId());
        orderResponsedto.setAmount(orderDetail.getAmount());
        orderResponsedto.setStatus(orderDetail.getStatus());

        return orderResponsedto;
    }
}
