package com.rohit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOSAlertDTO {
    private String status;   // ACTIVE, RESOLVED
    private Double latitude;
    private Double longitude;
}
