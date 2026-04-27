package com.logistics_system.entity;

import com.logistics_system.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@ToString
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double currentLat;
    private Double currentLng;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

}
