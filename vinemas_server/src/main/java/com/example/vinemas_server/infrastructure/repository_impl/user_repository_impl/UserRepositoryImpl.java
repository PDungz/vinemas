package com.example.vinemas_server.infrastructure.repository_impl.user_repository_impl;


import com.example.vinemas_server.domain.model.user.User;
import com.example.vinemas_server.domain.repository.user_repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, String>, UserRepository {

}