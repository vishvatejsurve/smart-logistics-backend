package com.logistics_system.service.impl;

import com.logistics_system.dto.driver.DriverRequestDTO;
import com.logistics_system.dto.driver.DriverResponseDTO;
import com.logistics_system.entity.Driver;
import com.logistics_system.enums.DriverStatus;
import com.logistics_system.mapper.DriverMapper;
import com.logistics_system.repository.DriverRepository;
import com.logistics_system.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    private DriverMapper driverMapper;

    @Override
    public DriverResponseDTO createDriver(DriverRequestDTO dto) {
       Driver driver = driverMapper.toEntity(dto);
       driver.setStatus(DriverStatus.AVAILABLE);
       return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public List<DriverResponseDTO> getAllDrivers() {
        return driverMapper.toDTOList(driverRepository.findAll());
    }

    @Override
    public DriverResponseDTO updateStatus(Long id, DriverStatus status) {
        Driver driver = driverRepository.findById(id).orElseThrow(()->new RuntimeException("Driver not found"));

     return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public DriverResponseDTO updateDriver(Long id, DriverRequestDTO dto) {
       Driver driver = driverRepository.findById(id).orElseThrow(()->new RuntimeException("Driver not found"));
       driver.setName(dto.getName());
       driver.setCurrentLat(dto.getCurrentLat());
       driver.setCurrentLng(dto.getCurrentLng());
       return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public void deleteDriver(Long id) {
       Driver driver = driverRepository.findById(id).orElseThrow(()->new RuntimeException("Driver not found"));
       if(driver.getStatus().equals(DriverStatus.BUSY)){
           throw new RuntimeException("Cannot delete busy driver");
       }
       driverRepository.delete(driver);
    }
}
