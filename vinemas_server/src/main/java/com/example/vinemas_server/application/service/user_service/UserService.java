package com.example.vinemas_server.application.service.user_service;
import com.example.vinemas_server.domain.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(String id);
    List<User> getAllUsers();  // <-- findAll
    User updateUser(String id, User user);
    void deleteUser(String id);
}