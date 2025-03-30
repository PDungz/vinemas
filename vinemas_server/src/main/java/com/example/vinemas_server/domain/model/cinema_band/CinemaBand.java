package com.example.vinemas_server.domain.model.cinema_band;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CinemaBand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaBand {
    @Id
    @Column(name = "cinema_band_id", updatable = false, nullable = false, length = 50)
    private String cinemaBandId;

    @Column(name = "name_cinema", length = 255, nullable = false)
    @NotBlank(message = "Cinema name cannot be empty")
    @Size(max = 255, message = "Cinema name cannot exceed 255 characters")
    private String nameCinema;

    @Column(name = "image_url", columnDefinition = "TEXT")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid image URL")
    private String imageUrl;

    @Column(name = "open_date")
    @PastOrPresent(message = "Open date cannot be in the future")
    private LocalDateTime openDate;

    @Column(name = "close_date")
    @FutureOrPresent(message = "Close date must be today or in the future")
    private LocalDateTime closeDate;

    @Column(name = "description", columnDefinition = "TEXT")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    // ✅ Tự động tạo UUID dạng String trước khi lưu vào DB
    @PrePersist
    protected void onCreate() {
        this.cinemaBandId = UUID.randomUUID().toString();
    }
}
