package com.rohit.entity;

import com.rohit.dto.SOSAlertDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SOSAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;  // ACTIVE / RESOLVED
    private Double latitude;
    private Double longitude;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor to convert from DTO
    public SOSAlert(SOSAlertDTO dto, User user) {
        this.status = dto.getStatus();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateFromDTO(SOSAlertDTO dto) {
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.status = dto.getStatus();
        this.updatedAt = LocalDateTime.now();
    }
}
