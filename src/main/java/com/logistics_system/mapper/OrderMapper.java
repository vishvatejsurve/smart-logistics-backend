package com.logistics_system.mapper;

import com.logistics_system.dto.order.OrderRequestDTO;
import com.logistics_system.dto.order.OrderResponseDTO;
import com.logistics_system.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequestDTO dto);
    OrderResponseDTO toDTO(Order order);

    List<OrderResponseDTO> toDTOList(List<Order> orders);
}
