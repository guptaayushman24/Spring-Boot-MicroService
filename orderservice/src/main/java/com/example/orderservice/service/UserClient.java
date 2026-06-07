package com.example.orderservice.service;

import com.example.orderservice.dto.UserExistRequestdto;
import com.example.orderservice.dto.UserExistResponsedto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userservice",url="http://localhost:8080/")
public interface UserClient {
    @PostMapping("api/finduser")
    UserExistResponsedto userExist (@RequestBody UserExistRequestdto userExistRequestdto);
}
