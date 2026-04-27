package com.logistics_system.service.impl;

import com.logistics_system.dto.order.OrderRequestDTO;
import com.logistics_system.dto.order.OrderResponseDTO;
import com.logistics_system.entity.Delivery;
import com.logistics_system.entity.Driver;
import com.logistics_system.entity.Order;
import com.logistics_system.enums.DriverStatus;
import com.logistics_system.enums.OrderStatus;
import com.logistics_system.exception.ResourceNotFoundException;
import com.logistics_system.mapper.OrderMapper;
import com.logistics_system.repository.DeliveryRepository;
import com.logistics_system.repository.DriverRepository;
import com.logistics_system.repository.OrderRepository;
import com.logistics_system.service.OrderService;
import com.logistics_system.util.DistanceUtil;
import com.logistics_system.util.ETAUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private final DriverRepository driverRepository;
    private final DeliveryRepository deliveryRepository;

    private static final Logger logger=LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
       Order order = orderMapper.toEntity(dto);
       order.setStatus(OrderStatus.CREATED);

      Order saved = orderRepository.save(order);
      return orderMapper.toDTO(saved);
    }

    @Override
    public  List<OrderResponseDTO> getAllOrders() {
      List<Order> orders = orderRepository.findAll();
      return orderMapper.toDTOList(orders);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
       Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
       return orderMapper.toDTO(order);
    }

    @Override
    public void deleteOrderById(Long id) {
       Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
       orderRepository.delete(order);
    }

    @Override
    public OrderResponseDTO updateOrder(Long id,OrderRequestDTO dto) {
       Order order =  orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not Found"));
       order.setUserId(dto.getUserId());
       order.setSourceLat(dto.getSourceLat());
       order.setSourceLng(dto.getSourceLng());
       order.setDestLat(dto.getDestLat());
       order.setDestLng(dto.getDestLng());
      return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDTO assignDriver(Long orderId) {

        logger.info("Assigning driver for order:{}",orderId);


       Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));

       if(order.getAssignedDriverId()!=null){
           throw new IllegalStateException("Driver already assigned to this order");
       }

        List<Driver>  drivers= driverRepository.findByStatus(DriverStatus.AVAILABLE);

      if (drivers.isEmpty()){
          throw  new RuntimeException("No Drivers available");
      }

      Driver bestDriver =null;
      double minDistance =Double.MAX_VALUE;
      double bestETA = Double.MAX_VALUE;

        for (Driver driver : drivers) {

            double distance = DistanceUtil.calculateDistance(
                    order.getSourceLat(),
                    order.getSourceLng(),
                    driver.getCurrentLat(),
                    driver.getCurrentLng()
            );
            double eta= ETAUtil.calculateETA(distance);

            logger.info("Driver {} -> Distance: {} km, ETA: {} mins",
                    driver.getId(), distance, eta);

            if (eta < bestETA) {
                bestETA = eta;
                minDistance = distance;
                bestDriver = driver;
            }
        }

        if (bestDriver == null){
            throw new RuntimeException("No suitable driver found");
        }

        order.setAssignedDriverId(bestDriver.getId());
        order.setStatus(OrderStatus.ASSIGNED);
        order.setEta(bestETA);

        bestDriver.setStatus(DriverStatus.BUSY);

        orderRepository.save(order);
        driverRepository.save(bestDriver);
        return orderMapper.toDTO(order);
    }

    @Transactional
    @Override
    public OrderResponseDTO markPicked(Long orderId) {
       Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));
       if (order.getStatus() !=OrderStatus.ASSIGNED){
           throw new RuntimeException("Order must be ASSIGNED to mark as PICKED");

       }
       order.setStatus(OrderStatus.PICKED);

        Delivery delivery=new Delivery();
        delivery.setOrderId(order.getId());
        delivery.setDriverId(order.getAssignedDriverId());
        delivery.setStartTime(LocalDateTime.now());

        deliveryRepository.save(delivery);

        return orderMapper.toDTO(orderRepository.save(order));

    }

    @Transactional
    @Override
    public OrderResponseDTO markDelivered(Long orderId) {

        logger.info("Marking order {} as DELIVERED",orderId);

       Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));

       if (order.getStatus() !=OrderStatus.PICKED){
           throw new RuntimeException("Order must be PICKED to mark as DELIVERED");
       }
       if (order.getAssignedDriverId() ==null){
           throw new IllegalStateException("No driver assigned to this order");
       }



      Driver driver = driverRepository.findById(order.getAssignedDriverId()).orElseThrow(()->new RuntimeException("Driver not found"));

      Delivery delivery = deliveryRepository.findByOrderId(orderId).orElseThrow(()->new ResourceNotFoundException("Delivery not found"));

        order.setStatus(OrderStatus.DELIVERED);
        driver.setStatus(DriverStatus.AVAILABLE);
        delivery.setEndTime(LocalDateTime.now());

        double distance = DistanceUtil.calculateDistance(
                order.getSourceLat(),
                order.getSourceLng(),
                order.getDestLat(),
                order.getDestLng()
        );

        delivery.setDistance(distance);

        driverRepository.save(driver);
        deliveryRepository.save(delivery);
        return orderMapper.toDTO(orderRepository.save(order));
    }
}
