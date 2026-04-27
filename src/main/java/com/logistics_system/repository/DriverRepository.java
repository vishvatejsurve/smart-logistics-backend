package com.logistics_system.repository;

import com.logistics_system.entity.Driver;
import com.logistics_system.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    List<Driver> findByStatus(DriverStatus status);
}
