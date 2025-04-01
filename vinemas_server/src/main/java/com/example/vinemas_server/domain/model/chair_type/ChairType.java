package com.example.vinemas_server.domain.model.chair_type;

import com.example.vinemas_server.domain.model.chair_config.ChairConfig;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "ChairTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChairType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chair_type_id", updatable = false, nullable = false)
    private Long chairTypeId;

    @ManyToOne
    @JoinColumn(name = "chair_config_id", nullable = false)
    private ChairConfig chairConfig;

    @Column(name = "seat_row", length = 5, nullable = false)
    @NotBlank(message = "Seat row cannot be empty")
    @Size(max = 5, message = "Seat row must be at most 5 characters")
    private String seatRow;

    @Column(name = "seat_type_name", length = 50, nullable = false)
    @NotBlank(message = "Seat type name cannot be empty")
    @Size(max = 50, message = "Seat type name must be at most 50 characters")
    private String seatTypeName;
}
