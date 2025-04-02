package com.example.vinemas_server.application.use_case.user_use_case;


import com.example.vinemas_server.application.service.user_service.UserService;
import com.example.vinemas_server.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {
    private final UserService userService;

    public CreateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public User execute(User user) {
        return userService.createUser(user);
    }
}