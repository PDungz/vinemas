package com.example.vinemas_server.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_auth_id", length = 50, nullable = false)
    private String userAuthId;

    @Column(name = "avatar_url", columnDefinition = "TEXT")
    private String avatarUrl;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "gender")
    private Integer gender; // 0: Female, 1: Male

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
}
