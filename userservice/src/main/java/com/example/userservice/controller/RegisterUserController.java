package com.example.userservice.controller;

import com.example.userservice.dto.UserExistRequestdto;
import com.example.userservice.dto.UserExistResponsedto;
import com.example.userservice.dto.UserRegisterRequestdto;
import com.example.userservice.dto.UserRegisterResponsedto;
import com.example.userservice.serviceImpl.RegisterUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RegisterUserController {
    private final RegisterUserServiceImpl registerUserServiceImpl;
    @PostMapping("/registeruser")
    public ResponseEntity<UserRegisterResponsedto> saveUser (@RequestBody UserRegisterRequestdto userRegisterRequestdto){
         return ResponseEntity.ok(registerUserServiceImpl.saveUser(userRegisterRequestdto));
    }

    @PostMapping("/finduser")
    public ResponseEntity<UserExistResponsedto> userExist (@RequestBody UserExistRequestdto userExistRequestdto){
        return ResponseEntity.ok(registerUserServiceImpl.userExistOrNot(userExistRequestdto));
    }
}
