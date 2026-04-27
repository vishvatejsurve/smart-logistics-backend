package com.logistics_system.controller;

import com.logistics_system.dto.driver.DriverRequestDTO;
import com.logistics_system.dto.driver.DriverResponseDTO;
import com.logistics_system.enums.DriverStatus;
import com.logistics_system.service.DriverService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@Valid @RequestBody DriverRequestDTO dto){
       DriverResponseDTO response = driverService.createDriver(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getAllDrivers(){
        return ResponseEntity.ok( driverService.getAllDrivers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DriverResponseDTO> updateStatus(@PathVariable Long id,
                                          @RequestParam DriverStatus status){
        return ResponseEntity.ok( driverService.updateStatus(id, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable Long id,
                                          @RequestBody DriverRequestDTO dto){
       return ResponseEntity.ok(driverService.updateDriver(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long id){
        driverService.deleteDriver(id);
        return ResponseEntity.ok( "Driver deleted Successfully");
    }

}
