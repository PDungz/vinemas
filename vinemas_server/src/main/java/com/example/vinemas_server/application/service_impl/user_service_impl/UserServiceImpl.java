package com.example.vinemas_server.application.service_impl.user_service_impl;

import com.example.vinemas_server.application.service.user_service.UserService;
import com.example.vinemas_server.domain.model.user.User;
import com.example.vinemas_server.domain.repository.user_repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsById(user.getUserAuthId())) {
            throw new IllegalArgumentException("User ID already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFullName(user.getFullName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    existingUser.setAvatarUrl(user.getAvatarUrl());
                    existingUser.setGender(user.getGender());
                    existingUser.setAddress(user.getAddress());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
