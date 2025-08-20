package com.rohit.entity;

import com.rohit.dto.LocationDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Location(LocationDTO dto, User user) {
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }
}
