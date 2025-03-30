package com.example.vinemas_server.domain.repository.user_repository;

import com.example.vinemas_server.domain.model.user.User;

import java.util.Optional;

import java.util.List;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    List<User> findAll();  // <-- findAll
    void deleteById(String id);
    boolean existsById(String id);
}
