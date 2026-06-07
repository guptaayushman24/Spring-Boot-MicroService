package com.example.userservice.service;

import com.example.userservice.dto.UserExistRequestdto;
import com.example.userservice.dto.UserExistResponsedto;
import com.example.userservice.dto.UserRegisterRequestdto;
import com.example.userservice.dto.UserRegisterResponsedto;

public interface RegisterUserService {
    UserRegisterResponsedto saveUser (UserRegisterRequestdto userRegisterRequestdto);
    UserExistResponsedto userExistOrNot (UserExistRequestdto userExistRequestdto);
}
