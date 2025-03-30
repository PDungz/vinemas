package com.example.vinemas_server.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "User ID cannot be empty")
    private String userAuthId;

    @Column(name = "avatar_url", columnDefinition = "TEXT")
    private String avatarUrl;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "email", length = 255, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column(name = "full_name", length = 255)
    @NotBlank(message = "Full name cannot be empty")
    @Size(max = 255, message = "Full name must be at most 255 characters")
    private String fullName;

    @Column(name = "gender", length = 1)
    @Min(value = 0, message = "Invalid gender value")
    @Max(value = 2, message = "Invalid gender value")
    private Integer gender; // 0: Female, 1: Male, 2: Other

    @Column(name = "phone_number", length = 15)
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
}
