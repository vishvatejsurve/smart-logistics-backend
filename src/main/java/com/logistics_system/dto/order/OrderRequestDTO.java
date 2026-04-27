package com.logistics_system.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "Source latitude is required")
    private Double sourceLat;

    @NotNull(message = "Source longitude is required")
    private Double sourceLng;

    @NotNull(message = "Destination latitude is required")
    private Double destLat;

    @NotNull(message = "Destination longitude is required")
    private Double destLng;
}
