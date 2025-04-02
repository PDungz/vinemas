package com.example.vinemas_server.domain.model.chair_config;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "ChairConfigs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChairConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chair_config_id", updatable = false, nullable = false)
    private Long chairConfigId;

    @Column(name = "layout", length = 50, nullable = false)
    @NotBlank(message = "Layout cannot be empty")
    private String layout;

    @Column(name = "row_count", length = 5)
    @NotNull(message = "Row count cannot be empty")
    private int rowCount;

    @Column(name = "seats_per_row", length = 5)
    @NotNull(message = "Seats per row cannot be empty")
    private int seatsPerRow;
}
