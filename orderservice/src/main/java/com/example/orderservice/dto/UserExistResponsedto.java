package com.example.orderservice.dto;

import lombok.Data;

@Data
public class UserExistResponsedto {
    private Long userId;
    private String name;
    private String email;

    public UserExistResponsedto(Long id, String name, String email) {
        this.userId = id;
        this.name = name;
        this.email = email;
    }
}
