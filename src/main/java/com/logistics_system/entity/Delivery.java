package com.logistics_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Setter
@Getter
@ToString
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long driverId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Double distance;
}
