package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserRegisterResponsedto {
    private Long userId;
    private String name;
    private String email;
}
