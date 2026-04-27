package com.logistics_system.service;

import com.logistics_system.dto.driver.DriverRequestDTO;
import com.logistics_system.dto.driver.DriverResponseDTO;
import com.logistics_system.enums.DriverStatus;

import java.util.List;

public interface DriverService {
    DriverResponseDTO createDriver(DriverRequestDTO dto);
    List<DriverResponseDTO> getAllDrivers();
    DriverResponseDTO updateStatus(Long id, DriverStatus status);
    DriverResponseDTO updateDriver(Long id,DriverRequestDTO dto);
    void deleteDriver(Long id);
}
