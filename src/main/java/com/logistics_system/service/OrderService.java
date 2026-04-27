package com.logistics_system.service;


import com.logistics_system.dto.order.OrderRequestDTO;
import com.logistics_system.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Long id);
    void deleteOrderById(Long id);
    OrderResponseDTO updateOrder(Long id,OrderRequestDTO dto);
    OrderResponseDTO assignDriver(Long orderId);
    OrderResponseDTO markPicked(Long orderId);
    OrderResponseDTO markDelivered(Long orderId);
}
