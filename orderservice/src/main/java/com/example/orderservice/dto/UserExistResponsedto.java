package com.example.orderservice.dto;

import lombok.Data;

@Data
public class UserExistResponsedto {
    private Long userId;
    private String name;
    private String email;
}
