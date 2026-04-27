package com.logistics_system.controller;

import com.logistics_system.dto.order.OrderRequestDTO;
import com.logistics_system.dto.order.OrderResponseDTO;
import com.logistics_system.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity< OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO dto){
        OrderResponseDTO  response = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/{id}/assign-driver")
    public OrderResponseDTO assignDriver(@PathVariable Long id) {
        return orderService.assignDriver(id);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
       return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id){
        return ResponseEntity.ok( orderService.getOrderById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id,
                                        @RequestBody OrderRequestDTO dto){
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PutMapping("/{id}/picked")
    public OrderResponseDTO markPicked(@PathVariable Long id){
        return orderService.markPicked(id);
    }
    @PutMapping("/{id}/delivered")
    public OrderResponseDTO markDelivered(@PathVariable Long id){
        return orderService.markDelivered(id);
    }
}
