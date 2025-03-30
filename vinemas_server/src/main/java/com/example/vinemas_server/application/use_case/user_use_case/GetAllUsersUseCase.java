package com.example.vinemas_server.application.use_case.user_use_case;

import com.example.vinemas_server.application.service.user_service.UserService;
import com.example.vinemas_server.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersUseCase {
    private final UserService userService;

    public GetAllUsersUseCase(UserService userService) {
        this.userService = userService;
    }

    public List<User> execute() {
        return userService.getAllUsers();
    }
}