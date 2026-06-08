package com.example.orderservice.serviceimpl;

import com.example.orderservice.dto.UserExistRequestdto;
import com.example.orderservice.dto.UserExistResponsedto;
import com.example.orderservice.service.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserClientService {

    private final UserClient userClient;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallback")
    public UserExistResponsedto getUser(UserExistRequestdto userExistRequestdto) {
        return userClient.userExist(userExistRequestdto);
    }

    public UserExistResponsedto fallback(UserExistRequestdto userExistRequestdto, Exception e) {
//        throw new ServiceUnavailableException(
//                "User Service is down. Cannot validate user.");
        return null;
    }
}