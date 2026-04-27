package com.logistics_system.dto.order;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private Double sourceLat;
    private Double sourceLng;
    private Double destLat;
    private Double destLng;
    private String status;
    private Long assignedDriverId;
    private Double eta;
}
