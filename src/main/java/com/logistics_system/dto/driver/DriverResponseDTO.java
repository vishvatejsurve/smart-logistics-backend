package com.logistics_system.dto.driver;

import lombok.Data;

@Data
public class DriverResponseDTO {
    private Long id;
    private String name;
    private Double currentLat;
    private Double currentLng;
    private String status;
}
