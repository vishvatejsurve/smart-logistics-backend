package com.logistics_system.entity;

import com.logistics_system.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Setter
@Getter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double eta;

    private Double sourceLat;
    private Double sourceLng;

    private Double destLat;
    private Double destLng;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long assignedDriverId;

}
