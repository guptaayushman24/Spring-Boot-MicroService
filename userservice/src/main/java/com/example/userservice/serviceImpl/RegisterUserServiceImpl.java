package com.example.userservice.serviceImpl;

import com.example.userservice.dto.UserExistRequestdto;
import com.example.userservice.dto.UserExistResponsedto;
import com.example.userservice.dto.UserRegisterRequestdto;
import com.example.userservice.dto.UserRegisterResponsedto;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {
    private final UserRepository userRepository;
    @Override
    public UserRegisterResponsedto saveUser(UserRegisterRequestdto userRegisterRequestdto) {
        User user = new User();
        UserRegisterResponsedto userRegisterResponsedto = new UserRegisterResponsedto();
        user.setName(userRegisterRequestdto.getName());
        user.setEmail(userRegisterRequestdto.getEmail());

        userRepository.save(user);

        userRegisterResponsedto.setUserId(user.getId());
        userRegisterResponsedto.setEmail(user.getEmail());
        userRegisterResponsedto.setName(user.getName());

        return userRegisterResponsedto;
    }

    @Override
    public UserExistResponsedto userExistOrNot(UserExistRequestdto userExistRequestdto) {
        UserExistResponsedto userExistResponsedto = new UserExistResponsedto();
        User user = userRepository.findById(userExistRequestdto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userExistRequestdto.getUserId()));
        userExistResponsedto.setUserId(user.getId());
        userExistResponsedto.setEmail(user.getEmail());
        userExistResponsedto.setName(user.getName());

        return userExistResponsedto;
    }
}
